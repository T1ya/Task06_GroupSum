package ait.numbers.model;

import ait.numbers.task.OneGroupSum;

import java.util.Arrays;

public class ParallelStreamGroupSum extends GroupSum{
    public ParallelStreamGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        return Arrays.stream(numberGroups).parallel().mapToInt(group -> Arrays.stream(group).sum()).sum();
    }
}
