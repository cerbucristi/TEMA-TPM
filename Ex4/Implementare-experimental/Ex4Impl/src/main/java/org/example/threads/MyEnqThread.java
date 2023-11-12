package org.example.threads;

import org.example.queuesimplementations.BasicQueue;

public class MyEnqThread extends Thread{

    BasicQueue basicQueue;
    int x;

    public MyEnqThread(BasicQueue basicQueue, int x) {
        this.basicQueue = basicQueue;
        this.x = x;
    }

    @Override
    public void run (){
        basicQueue.enq(x);
    }
}
