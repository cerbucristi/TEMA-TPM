package org.example.queuesimplementations;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import static org.example.constans.Constants.Common.QSIZE;
import static org.example.constans.Constants.Common.maxQSize;

public class DoubleLockBased implements BasicQueue{
    int head = 0, tail = 0;
    int items [] = new int[ QSIZE ];

    List<Integer> allItems = new LinkedList<>();
    List<Integer> dequedItems = new LinkedList<>();
    ReentrantLock enqlock = new ReentrantLock();
    ReentrantLock deqlock = new ReentrantLock();

    public void enq(int x) {
        while ( tail - head == QSIZE ) {};
        enqlock.lock();
        try {
            items [ tail % QSIZE ] = x;
            allItems.add(x);
            tail ++;
        } finally {
            enqlock.unlock();
        }
    }

    public int deq () {
        while ( tail == head ) {};
        deqlock.lock();
        try {
            int item = items [ head % QSIZE ];
            head ++;
            dequedItems.add(item);
            return item;
        } finally {
            deqlock.unlock();
        }
    }

    @Override
    public String toString() {
        return "DoubleLockBased{" +
                "head=" + head +
                ", tail=" + tail +
                ", items=" + Arrays.toString(items) + "\n" +
                ", allItems=    " + allItems.toString() + "\n" +
                ", dequed Items=" + dequedItems.toString() + "\n" +
                ", enqlock=" + enqlock +
                ", deqlock=" + deqlock +
                '}';
    }
}