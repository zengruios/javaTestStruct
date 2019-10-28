package com.hikvision.bigdata.ude.java.struct;

/**
 * @Description: 数组测试
 * @Author: zengrui
 * @CreateTime: 2019/10/28 11:39
 */
public class ArrayTest {

    private int[] array;
    private int capacity;
    public int size = 0;

    public ArrayTest(int capacity) {
        this.array = new int[capacity];
        this.capacity = capacity;
    }

    public void insertIntArray(int value) {
        for (int i = 0; i < capacity; ++i) {
            if (this.array[i] == value) {
                System.out.println("hit cache: " + value);
                return;
            }
        }
        if (size < capacity) {
            this.array[size] = value;
            size ++;
            System.out.println("add to cache: " + value);
        } else {
            // LRU
            System.out.println("expired from cache: " + this.array[0]);
            System.arraycopy(this.array, 1, this.array, 0, capacity - 1);
            System.out.println("add to cache: " + value);
            this.array[capacity - 1] = value;
        }
    }

    public static void main(String[] args) throws Exception {
        ArrayTest arrTest = new ArrayTest(16);
        for (int i = 1; i < 17; ++i) {
            arrTest.insertIntArray(i);
        }
        // 缓存命中
        arrTest.insertIntArray(10);
        arrTest.insertIntArray(3);
        // LRU缓存淘汰
        arrTest.insertIntArray(17);

        // System.copy
        ArrayTest[] objectArray = new ArrayTest[2];
        ArrayTest[] destArray = new ArrayTest[2];
        objectArray[0] = new ArrayTest(100);
        objectArray[1] = new ArrayTest(200);
        System.arraycopy(objectArray, 0, destArray, 0, 2);

        objectArray[0].capacity = 101;
        System.out.println("Finished.");
    }
}
