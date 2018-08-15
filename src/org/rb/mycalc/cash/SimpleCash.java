package org.rb.mycalc.cash;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Cache for T type value having related Pair value: T => Pair.
 * Organized as Synchronized map, where key = T, value = Pair. 
 * @author raitis
 * @param <T>
 */
public class SimpleCash <T extends Cashable>{

    private boolean test= false;
    private final Map<T,Pair > cash = Collections.synchronizedMap(new HashMap<>());
    
    private int Threshold = 100;

    
    
    public SimpleCash() {
       
    }

    public SimpleCash(int threshold){
        this.Threshold = threshold;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    
    public Pair take(T obj){
        Pair result=null;
        if(cash.containsKey(obj)){
            result = cash.get(obj);
            if(test)
            System.out.println("take():get, size= "+cash.size()+", "
                    + "Thr= "+Thread.currentThread().getName()+", T= "+System.currentTimeMillis());
           
        }else{
            result = obj.value();
            if(cash.size()< Threshold)
                cash.put(obj, result);
            if(test)
            System.out.println("take():put,get, size= "+cash.size()+", "
                    + "Thr= "+Thread.currentThread().getName()+", T= "+System.currentTimeMillis());
        }
        //cacheMaintain();
         return result;
    }
    
    public int size(){
       return this.cash.size();
    }
    
    private void cacheMaintain(){
        if(cash.size()> Threshold){
            System.out.println("cacheMaintain(): clearing...sz= "+cash.size());  
          cash.clear();
         
        }
        
    }

    public void printCash(){
        System.out.println("Cash size= "+cash.size());
        int counter=0;
        for (Map.Entry<T, Pair> entry : cash.entrySet()) {
            System.out.printf("%d: %s : %s\n",counter++,entry.getKey(),entry.getValue());
        }
    }
}
