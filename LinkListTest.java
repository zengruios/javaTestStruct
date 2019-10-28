package com.hikvision.bigdata.ude.java.struct;

/**
 * @Description: 链表测试
 * @Author: zengrui
 * @CreateTime: 2019/10/28 10:11
 */
public class LinkListTest {
    static class ListNode<T> {
        T data;
        ListNode head;
        ListNode tail;
        ListNode prev;
        ListNode next;
        ListNode() {}
        ListNode(T value) {
            this.data = value;
        }
        //  单向链表尾部插入
        public ListNode<T> insertList(T value) {
            if (this.data == null && this.next == null) {
                this.data = value;
            } else {
                ListNode<T> current = this;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = new ListNode(value);
            }
            return this;
        }
        // 双向链表插入
        public ListNode<T> insertDoubleList(T value) {
            if (this.data == null && this.next == null) {
                // 空链表
                this.data = value;
                this.head = this;
                this.tail = this;
            } else {
                ListNode newNode = new ListNode(value);
                this.tail.next = newNode;
                newNode.prev = this.tail;
                this.tail = newNode;
            }
            return this;
        }
    }
    public static void main(final String[] args) throws Exception {
        // 单向链表
        ListNode<Integer> listTest = new ListNode(100);
        listTest.next = new ListNode(99);
        listTest.next.next = new ListNode(98);
        //listTest.next.next.next = listTest;    // 循环链表
        listTest.insertList(97).insertList(96);
        System.out.println(listTest);
        // 双向链表
        ListNode<Integer> doubleListTest = new ListNode();
        doubleListTest.insertDoubleList(100).insertDoubleList(99).insertDoubleList(98);

        // 回文串
//        byte[] strArray = new byte[] {'a', 'b', 'c', 'd', 'd', 'e', 'f', 'g'};
//        byte[] strArray = new byte[] {'a', 'b', 'c', 'd', 'd', 'c', 'b', 'a'};
        byte[] strArray = new byte[] {'a', 'b', 'c', 'd', 'c', 'b', 'a'};
        ListNode<String> listTestPalindrome = new ListNode();
        for (int i = 0; i < strArray.length; ++i) {
            listTestPalindrome.insertList(new String(strArray, i, 1));
        }

        ListNode<String> slow = listTestPalindrome;
        ListNode<String> fast = listTestPalindrome;
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
