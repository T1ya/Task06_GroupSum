package ait.numbers.model;

import ait.numbers.task.OneGroupSum;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadGroupSum extends GroupSum {
    public ThreadGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        List<Thread> threads = new ArrayList<>();
        List<OneGroupSum> tasks = new ArrayList<>();
        AtomicInteger sum = new AtomicInteger(0);
        //fill tasks, thread(tasks) and start threads
        for(int[] group: numberGroups) {
            OneGroupSum task = new OneGroupSum(group);
            Thread thread = new Thread(task);
            threads.add(thread);
            tasks.add(task);

            thread.start();
        }
        //join threads
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        //aggregate sum
        tasks.forEach(task-> sum.addAndGet(task.getSum()));

        return sum.get();
    }
}
