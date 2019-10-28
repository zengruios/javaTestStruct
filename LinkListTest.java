package com.zengrui.java.struct;

/**
 * @Description: 链表测试
 * @Author: zengrui
 * @CreateTime: 2019/10/28 10:11
 */
public class LinkListTest {
    ListNode head;
    ListNode tail;
    LinkListTest() {}
    static class ListNode<T> {
        T data;
        ListNode prev;
        ListNode next;
        ListNode() {}
        ListNode(T value) {
            this.data = value;
        }
    }

    //  单向链表尾部插入
    public <T> LinkListTest insertList(T value) {
        ListNode<T> newNode = new ListNode<>(value);
        if (this.head == null && this.tail == null) {
            this.head = this.tail = newNode;
        } else {
            this.tail.next = newNode;
            this.tail = newNode;
        }
        return this;
    }
    // 双向链表尾部插入
    public <T> LinkListTest insertDoubleList(T value) {
        ListNode<T> newNode = new ListNode<>(value);
        if (this.head == null && this.tail == null) {
            // 空链表
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            newNode.prev = this.tail;
            this.tail = newNode;
        }
        return this;
    }

    public static void main(final String[] args) throws Exception {
        // 单向链表
        LinkListTest listTest = new LinkListTest();
        listTest.insertList(97).insertList(96).insertList(95);
        System.out.println("head: " + listTest.head + ", tail: " + listTest.tail);
        // 循环链表
        listTest.tail.next = listTest.head;
        // 双向链表
        LinkListTest doubleListTest = new LinkListTest();
        doubleListTest.insertDoubleList(100).insertDoubleList(99).insertDoubleList(98);

        // 回文串
        byte[] strArray = new byte[] {'a', 'b', 'c', 'd', 'd', 'e', 'f', 'g'};
//        byte[] strArray = new byte[] {'a', 'b', 'c', 'd', 'd', 'c', 'b', 'a'};
//        byte[] strArray = new byte[] {'a', 'b', 'c', 'd', 'c', 'b', 'a'};
        LinkListTest listTestPalindrome = new LinkListTest();
        for (int i = 0; i < strArray.length; ++i) {
            listTestPalindrome.insertList(new String(strArray, i, 1));
        }

        ListNode<String> slow = listTestPalindrome.head;
        ListNode<String> fast = listTestPalindrome.head;
        ListNode<String> prev = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode<String> next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }
        if (fast != null) {
            slow = slow.next;
        }

        while (slow != null) {
            if (!slow.data.equals(prev.data)) {
                System.out.println("No Palindrome");
                return;
            }
            slow = slow.next;
            prev = prev.next;
        }
        System.out.println("Palindrome");

        System.out.println("Finished");
    }
}
