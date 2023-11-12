package org.example.queuesimplementations;

import java.util.Arrays;

import static org.example.constans.Constants.Common.QSIZE;
import static org.example.constans.Constants.Common.maxQSize;

public class LockFree implements BasicQueue{
    int head = 0, tail = 0;
    int items [] = new int[ QSIZE ];

    int allItems [] = new int[maxQSize];

    public void enq(int x) {
        while ( tail - head == QSIZE ) {};
        items [ tail % QSIZE ] = x;
        allItems[ tail ] = x;
        tail ++;
    }

    public int deq () {
        while ( tail == head ) {};
        int item = items [ head % QSIZE ];
        head ++;
        System.out.println("Item deq:" + item);
        return item;
    }

    @Override
    public String toString() {
        return "LockFree{" +
                "head=" + head +
                ", tail=" + tail +
                ", items=" + Arrays.toString(items) + "\n" +
                ", allItems=" + Arrays.toString(allItems) +
                '}';
    }
}