package org.example.threads;

import org.example.queuesimplementations.BasicQueue;

import java.util.List;

public class MyDeqThread extends Thread{

    BasicQueue basicQueue;

    public MyDeqThread(BasicQueue basicQueue) {
        this.basicQueue = basicQueue;
    }

    @Override
    public void run (){
        basicQueue.deq();
    }
}
