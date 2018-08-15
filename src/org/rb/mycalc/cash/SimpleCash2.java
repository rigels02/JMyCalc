package org.rb.mycalc.cash;

import java.util.HashMap;
import java.util.Map;

/**
 * Like SimpleCash , but not used conditional locks. For threads synchronization
 * 'synchronized' by cash object , is used.
 *
 * @author raitis
 * @param <T>
 */
public class SimpleCash2<T extends Cashable> {

    private boolean test = false;
    private final Map<T, Pair> cash = new HashMap<>();

    private int Threshold = 100;

    public SimpleCash2() {

    }

    public SimpleCash2(int threshold) {
        this.Threshold = threshold;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public Pair take(T obj) {
        Pair result = null;
        boolean found = false;

        if (cash.containsKey(obj)) {
            found = true;
            result = cash.get(obj);
            if (test) {
                System.out.println("take():get, size= " + cash.size() + ", "
                        + "Thr= " + Thread.currentThread().getName() + ", T= " + System.currentTimeMillis());
            }

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

    private Pair add(T obj) {

        Pair result = obj.value();
        synchronized (cash) {
            if (cash.size() < Threshold) {
                cash.put(obj, result);
            }
        }
        return result;

    }

    public int size() {
        synchronized (cash) {
            return this.cash.size();
        }
    }

    private synchronized void cacheMaintain() {
        if (cash.size() > Threshold) {
            System.out.println("cacheMaintain(): clearing...sz= " + cash.size());
            cash.clear();

        }

    }

    public synchronized void printCash() {
        System.out.println("Cash size= " + cash.size());
        int counter = 0;

        for (Map.Entry<T, Pair> entry : cash.entrySet()) {
            System.out.printf("%d: %s : %s\n", counter++, entry.getKey(), entry.getValue());
        }
    }
}
