package fun;

import java.util.*;
import java.util.concurrent.*;

/**
 * 每个支付方式通过一个线程去启动一个远程调用线程去请求可用性，如果远程调用超时则降级。
 */
public class PaymentList {
    private ExecutorService pool = new ThreadPoolExecutor(32, 64, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    public static final long TIMEOUT = 1000;

    private List<String> paymentAddress() {
        return Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N");
    }

    private String queryPayment(String address) {
        try {
            Thread.sleep(new Random().nextInt(2000));
        } catch (InterruptedException e) {
            return null;
        }
        return address + " -> available";
    }

    public List<String> availablePayment() {
        List<String> addresses = paymentAddress();
        CountDownLatch latch = new CountDownLatch(addresses.size());
        List<String> ret = Collections.synchronizedList(new ArrayList<>());
        try {
            for (String address : addresses) {
                pool.submit(() -> {
                    Future<String> f = pool.submit(() -> queryPayment(address));
                    String s = null;
                    try {
                        s = f.get(TIMEOUT, TimeUnit.MILLISECONDS);
                    } catch (Exception e) {
                        f.cancel(true);
                        System.out.println(address + " -> not available");
                        s = null;
                    } finally {
                        if (s != null) ret.add(s);
                        latch.countDown();
                    }
                });
            }
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return ret;
    }

    void shutdown() {
        pool.shutdownNow();
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        PaymentList paymentList = new PaymentList();
        System.out.println(paymentList.availablePayment());
        paymentList.shutdown();
        System.out.println(System.currentTimeMillis() - start);
    }
}
