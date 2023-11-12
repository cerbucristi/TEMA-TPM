package org.example;

import org.example.queuesimplementations.BasicQueue;
import org.example.queuesimplementations.DoubleLockBased;
import org.example.queuesimplementations.LockFree;
import org.example.queuesimplementations.SingleLockBased;
import org.example.threads.MyDeqThread;
import org.example.threads.MyEnqThread;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Random random = new Random();
//        System.out.println("Free lock queue:");
//
//
//        BasicQueue basicQueueFree = new LockFree();
//        int nrOfFreeThreads = 2;
//        Thread freeThreads[] = new Thread[nrOfFreeThreads];
//
//        //Free lock
//
//        freeThreads[0] = new MyEnqThread(basicQueueFree, random.nextInt(10));
//        freeThreads[1] = new MyDeqThread(basicQueueFree);
//
//        for (Thread thread : freeThreads) {
//            thread.start();
//        }
//
//        try {
//            for (Thread thread : freeThreads) {
//                thread.join();
//            }
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println(basicQueueFree);

        int nrOfThreads = 20;

        //Single lock
//        System.out.println("Single lock queue:");
//
//        basicQueueFree = new SingleLockBased();
//
//        Thread[] threads = new Thread[nrOfThreads*2];
//        for (int i = 0; i < nrOfThreads*2; i+=2) {
//            threads[i] = new MyEnqThread(basicQueueFree, random.nextInt());
//            threads[i+1] = new MyDeqThread(basicQueueFree);
//        }
//
//        for (Thread thread : threads) {
//            thread.start();
//        }
//
//        try {
//            for (Thread thread : threads) {
//                thread.join();
//            }
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println(basicQueueFree);

        //Double lock
        System.out.println("Double lock queue:");

        BasicQueue basicQueueFree = new DoubleLockBased();
        List<Integer> deqList = new LinkedList<>();


        Thread doubleThreads[] = new Thread[nrOfThreads*2];
        for (int i = 0; i < nrOfThreads*2; i+=2) {
            doubleThreads[i] = new MyEnqThread(basicQueueFree, random.nextInt(10));
            doubleThreads[i+1] = new MyDeqThread(basicQueueFree);
        }

        for (Thread thread : doubleThreads) {
            thread.start();
        }

        try {
            for (Thread thread : doubleThreads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(basicQueueFree);

    }
}