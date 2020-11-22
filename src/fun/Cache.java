package fun;

import java.util.*;

public class Cache {
    long duration = 1800;

    Map<String, Map<String, Object>> cache = new HashMap<>();
    Set<String> idSet = new HashSet<>();

    public Cache() {
        idSet.addAll(Arrays.asList("A", "B"));
    }

    private Map<String, Object> update(String id) {
        Map<String, Object> user = latestUser(id);
        cache.put(id, user);
        return user;
    }

    private Map<String, Object> latestUser(String id) {
        if (!idSet.contains(id)) return null;
        Map<String, Object> user = new HashMap<>();
        user.put("id", id);
        user.put("name", "Shane");
        user.put("address", "China");
        user.put("expire", System.currentTimeMillis());
        user.put("code", new Random().nextInt());
        return user;
    }

    public Map<String, Object> query(String id) {
        if (cache.get(id) == null) return update(id);
        Map<String, Object> user = cache.get(id);
        long expire = (long) user.get("expire");
        if ((System.currentTimeMillis() - expire) / 1000 > duration) return update(id);
        return user;
    }

    public static void main(String[] args) throws InterruptedException {
        Cache cache = new Cache();
        System.out.println(cache.query("A"));
        System.out.println(cache.query("A"));
        System.out.println(cache.query("B"));
        System.out.println(cache.query("B"));
        System.out.println(cache.query("E"));
        System.out.println(cache.query("A"));
        System.out.println(cache.query("B"));
    }

}
