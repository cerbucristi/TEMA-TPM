package org.example.queuesimplementations;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

import static org.example.constans.Constants.Common.QSIZE;
import static org.example.constans.Constants.Common.maxQSize;

public class SingleLockBased implements BasicQueue{
    int head = 0, tail = 0;
    int items [] = new int[ QSIZE ];
    int allItems [] = new int[ maxQSize ];
    ReentrantLock lock = new ReentrantLock();

    public void enq(int x) {
        lock.lock();
        try {
            while ( tail - head == QSIZE ) {};
            items [ tail % QSIZE ] = x;
            allItems[ tail ] = x;
            tail ++;
        } finally {
            lock.unlock();
        }
    }

    public int deq () {
        lock.lock();
        try {
            while ( tail == head ) {};
            int item = items [ head % QSIZE ];
            head ++;
            System.out.println("Item deq:" + item);
            return item;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return "SingleLockBased{" +
                "head=" + head +
                ", tail=" + tail +
                ", items=" + Arrays.toString(items) + "\n" +
                ", allItems=" + Arrays.toString(allItems) +
                ", lock=" + lock +
                '}';
    }
}