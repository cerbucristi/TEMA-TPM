package org.example;

public class MutualExclusionAlgorithm {
    private boolean[] flag;
    private boolean[] access;
    private int[] label;
    private int n;

    public MutualExclusionAlgorithm(int n) {
        this.n = n;
        flag = new boolean[n];
        access = new boolean[n];
        label = new int[n];
    }

    public void init() {
        for (int k = 0; k < n; k++) {
            flag[k] = false;
            access[k] = false;
            label[k] = k + 1;
        }
    }

    public void lock(int i) {
        flag[i] = true;
        do {
            access[i] = false;
            awaitCondition(i);
            access[i] = true;
        } while (existsJWithAccess(i));
    }

    private void awaitCondition(int i) {
        for (int j = 0; j < flag.length; j++) {
            if (j != i && (flag[j] == false || label[j] > label[i])) {

            }
        }
    }

    private boolean existsJWithAccess(int i) {
        for (int j = 0; j < access.length; j++) {
            if (j != i && access[j] == true) {
                return true;
            }
        }
        return false;
    }

    public void unlock(int i) {
        rebuildLabelLimitedPriority(label, i);
//        label[i] = getMaxLabel() + 1;
        access[i] = false;
        flag[i] = false;
    }

    //homework implementation
    private int getMaxLabel() {
        int maxLabel = Integer.MIN_VALUE;
        for (int k = 0; k < label.length; k++) {
            if (label[k] > maxLabel) {
                maxLabel = label[k];
            }
        }
        return maxLabel;
    }

    //Modified part:
    private void rebuildLabelLimitedPriority (int[] label, int currentThreadId) {

            for (int j = 0; j < n; j++) {
                if (label[j] > label[currentThreadId]) {
                    label[j] = --label[j];
                }
            }
            label[currentThreadId] = n; //lowest priority for the thread who calls unlock
    }

    public int[] getLabel() {
        return label;
    }
}
