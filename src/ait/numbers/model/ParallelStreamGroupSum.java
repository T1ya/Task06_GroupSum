package ait.numbers.model;

import ait.numbers.task.OneGroupSum;

import java.util.Arrays;

public class ParallelStreamGroupSum extends GroupSum{
    public ParallelStreamGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        // TODO * ParallelStreamGroupSum, use parallel stream
        return Arrays.stream(numberGroups).parallel().mapToInt(group -> {
            OneGroupSum task = new OneGroupSum(group);
            task.run();
            return task.getSum();
        }).sum();
    }
}
