package ait.numbers.model;

import ait.numbers.task.OneGroupSum;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorGroupSum extends GroupSum{
    public ExecutorGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        List<OneGroupSum> tasks = new ArrayList<>();
        AtomicInteger sum = new AtomicInteger(0);
        for (int[] group: numberGroups) {
            tasks.add(new OneGroupSum(group));
        }
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        tasks.forEach(executorService::execute);
        executorService.shutdown();
        //try to wait for the termination...
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        tasks.forEach(task->sum.addAndGet(task.getSum()));
        return sum.get();
    }
}
