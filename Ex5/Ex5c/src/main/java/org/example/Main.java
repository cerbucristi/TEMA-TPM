package org.example;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int n = 10;

        MutualExclusionAsInHomework mutualExclusionAlgorithm = new MutualExclusionAsInHomework(n);
        mutualExclusionAlgorithm.init();

        int indexToLock = 0;
        System.out.println("Initial label array: \n");
        System.out.println(Arrays.toString(mutualExclusionAlgorithm.getLabel()));
        mutualExclusionAlgorithm.lock(indexToLock);
        mutualExclusionAlgorithm.unlock(indexToLock);
        System.out.println("Label array after 1 execution: \n");
        System.out.println(Arrays.toString(mutualExclusionAlgorithm.getLabel()));
        mutualExclusionAlgorithm.lock(indexToLock);
        mutualExclusionAlgorithm.unlock(indexToLock);
        System.out.println("Label array after 2 executions: \n");
        System.out.println(Arrays.toString(mutualExclusionAlgorithm.getLabel()));

        System.out.println("\n Implemented Variant: \n");

        MutualExclusionAlgorithm mutualExclusionAlgorithmImplVar = new MutualExclusionAlgorithm(n);
        mutualExclusionAlgorithmImplVar.init();

        int indexToLockImplVar = 0;
        System.out.println("Initial label array: \n");
        System.out.println(Arrays.toString(mutualExclusionAlgorithmImplVar.getLabel()));
        mutualExclusionAlgorithmImplVar.lock(indexToLockImplVar);
        mutualExclusionAlgorithmImplVar.unlock(indexToLockImplVar);
        System.out.println("Label array after execution on index:" + indexToLockImplVar + " \n");
        System.out.println(Arrays.toString(mutualExclusionAlgorithmImplVar.getLabel()));
        mutualExclusionAlgorithmImplVar.lock(2);
        mutualExclusionAlgorithmImplVar.unlock(2);
        System.out.println("Label array after execution on index:" + 2 + " \n");
        System.out.println(Arrays.toString(mutualExclusionAlgorithmImplVar.getLabel()));
    }

}