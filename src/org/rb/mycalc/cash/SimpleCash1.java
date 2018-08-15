package org.rb.mycalc.cash;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Like SimpleCash , but use not synchronized HashMap. For threads
 * synchronization notRead/notModify conditional locks are used.
 *
 * @author raitis
 * @param <T>
 */
public class SimpleCash1<T extends Cashable> {

    private boolean test = false;
    private final Map<T, Pair> cash = new HashMap<>();

    private final Lock lock = new ReentrantLock();
    private boolean read = false;
    private final Condition notRead = lock.newCondition();
    private boolean modify = false;
    private final Condition notModify = lock.newCondition();

    private int Threshold = 100;

    public SimpleCash1() {

    }

    public SimpleCash1(int threshold) {
        this.Threshold = threshold;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public Pair take(T obj) throws InterruptedException {
        Pair result = null;
        boolean found = false;
        lock.lock();
        try {
            while (modify) {
                notModify.await();
            }
            read= true;
            if (cash.containsKey(obj)) {
                found =true;
                result = cash.get(obj);
                if (test) {
                    System.out.println("take():get, size= " + cash.size() + ", "
                            + "Thr= " + Thread.currentThread().getName() + ", T= " + System.currentTimeMillis());
                }

            }
        } finally {
            read= false;
            notRead.signalAll();
            lock.unlock();
        }
        if (!found) {
            result = add(obj);
            if (test) {
                System.out.println("take():put,get, size= " + cash.size() + ", "
                        + "Thr= " + Thread.currentThread().getName() + ", T= " + System.currentTimeMillis());
            }
        }
        //cacheMaintain();
        return result;
    }

    private Pair add(T obj) throws InterruptedException {
        lock.lock();
        try {
            while (read) {
                notRead.await();
            }
            Pair result = obj.value();
            modify = true;
            if (cash.size() < Threshold) {
                cash.put(obj, result);
            }
            return result;
        } finally {
            modify = false;
            notModify.signalAll();
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return this.cash.size();
        } finally {
            lock.unlock();
        }
    }

    private void cacheMaintain() {
        if (cash.size() > Threshold) {
            System.out.println("cacheMaintain(): clearing...sz= " + cash.size());
            cash.clear();

        }

    }

    public void printCash() {
        System.out.println("Cash size= " + cash.size());
        int counter = 0;
        for (Map.Entry<T, Pair> entry : cash.entrySet()) {
            System.out.printf("%d: %s : %s\n", counter++, entry.getKey(), entry.getValue());
        }
    }
}
