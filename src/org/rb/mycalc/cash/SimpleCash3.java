package org.rb.mycalc.cash;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Like SimpleCash2, but for cash the ArrayList is used. 
 * For threads synchronization
 * 'synchronized' by cash object , is used.
 * 
 * @author raitis
 * @param <T>
 */
public class SimpleCash3 <T extends Cashable>{
 
    class Cell{
        T       first;
        Pair    second;

        public Cell(T first, Pair second) {
            this.first = first;
            this.second = second;
        }
        
    };
    
    private boolean test = false;
    private final List<Cell> cash = new ArrayList<>();

    private int Threshold = 100;

    public SimpleCash3() {

    }

    public SimpleCash3(int threshold) {
        this.Threshold = threshold;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public Pair take(T obj) {
        Pair result = null;
        boolean found = false;

        int Size = cash.size();
        for(int i=0; i< Size; i++){
            if(cash.get(i).first.equals(obj)){
                found = true;
                result = cash.get(i).second;
                if (test) {
                System.out.println("take():get, size= " + Size + ", "
                        + "Thr= " + Thread.currentThread().getName() + ", T= " + System.currentTimeMillis());
                }
             break;   
            }
        }
        
        if (!found) {
            result = add(obj, Size);
            if (test) {
                System.out.println("take():put,get, size= " + Size + ", "
                        + "Thr= " + Thread.currentThread().getName() + ", T= " + System.currentTimeMillis());
            }
        }
        //cacheMaintain();
        return result;
    }

    private Pair add(T obj, int oldSize) {

        Pair result = obj.value();
        synchronized (cash) {
            int newSize = cash.size();
            //check that other thread has not added required obj.
            for(int i= oldSize; i< newSize; i++){
                if(cash.get(i).first.equals(obj))
                    return result;
            }
            //not found
            if (newSize < Threshold) {
                Cell cell = new Cell(obj, result);
                cash.add(cell);
                
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

        for (Cell cell : cash) {
            System.out.printf("%d: %s : %s\n", counter++, cell.first, cell.second);
        }
        
    }
}
