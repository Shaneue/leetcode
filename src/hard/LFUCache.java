package hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.
 * <p>
 * Note that the number of times an item is used is the number of calls to the get and put functions for that item since it was inserted. This number is set to zero when the item is removed.
 * <p>
 * <p>
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * <p>
 * <p>
 * Example:
 * <p>
 * LFUCache cache = new LFUCache( 2 );
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);      // returns 1
 * cache.put(3,3);    // evicts key 2
 * cache.get(2);      // returns -1 (not found)
 * cache.get(3);      // returns 3.
 * cache.put(4,4);    // evicts key 1.
 * cache.get(1);      // returns -1 (not found)
 * cache.get(3);      // returns 3
 * cache.get(4);      // returns 4
 * <p>
 * 数据结构是一个有序双向链表，用两个map分别记录key与frequency到节点的映射
 */
public class LFUCache {
    static class Node {
        Node prev, next;
        int key, value;
        int frequency;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    int capacity;
    Node head = new Node(0, 0);
    Node tail = new Node(0, 0);
    Map<Integer, Node> keyMap = new HashMap<>();
    Map<Integer, Node> frequencyMap = new HashMap<>();
    int size = 0;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        head.frequency = 0;
        tail.frequency = Integer.MAX_VALUE;
        head.next = tail;
        tail.prev = head;
        frequencyMap.put(head.frequency, head);
        frequencyMap.put(tail.frequency, tail);
    }

    public int get(int key) {
        Node node = keyMap.get(key);
        if (node == null) {
            return -1;
        }
        promote(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;
        Node node = keyMap.get(key);
        if (node == null) {
            if (size >= capacity) {
                rmLFU();
                size--;
            }
            Node nodeNew = new Node(key, value);
            nodeNew.frequency = 1;
            Node frequencyOne = frequencyMap.get(1);
            if (frequencyOne == null) {
                insertAfter(head, nodeNew);
            } else {
                insertAfter(frequencyOne, nodeNew);
            }
            size++;
            frequencyMap.put(1, nodeNew);
            keyMap.put(key, nodeNew);
        } else {
            promote(node);
            node.value = value;
        }
    }
    
    void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    void insertAfter(Node who, Node node) {
        Node next = who.next;
        who.next = node;
        node.next = next;
        next.prev = node;
        node.prev = who;
    }

    void promote(Node node) {
        int f = node.frequency;
        node.frequency++;
        Node prev = node.prev;
        Node list = frequencyMap.get(f);
        Node nextList = list.next;
        if (node == list) {
            if (prev.frequency == f) {
                frequencyMap.put(f, prev);
            } else {
                frequencyMap.remove(f);
            }
            if (nextList.frequency == f + 1) {
                remove(node);
                insertAfter(frequencyMap.get(f + 1), node);
            }
        } else {
            remove(node);
            if (nextList.frequency == f + 1) {
                insertAfter(frequencyMap.get(f + 1), node);
            } else {
                insertAfter(list, node);
            }
        }
        frequencyMap.put(f + 1, node);
    }

    void rmLFU() {
        Node node = head.next;
        Node next = node.next;
        remove(node);
        keyMap.remove(node.key);
        if (node.frequency != next.frequency) {
            frequencyMap.remove(node.frequency);
        }
    }
}
