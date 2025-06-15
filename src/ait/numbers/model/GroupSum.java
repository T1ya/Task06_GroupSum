package ait.numbers.model;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class GroupSum {
    protected int[][] numberGroups;

    public GroupSum(int[][] numberGroups) {
        this.numberGroups = numberGroups;
    }

    public abstract int computeSum();
}
