package org.rb.mycalc.cash;

/**
 *
 * @author raitis
 */
public class Pair {

    private final double value;
    
    private final double dvalue;

    public Pair(double value, double dvalue) {
        this.value = value;
        this.dvalue = dvalue;
    }

    public double getValue() {
        return value;
    }

    public double getDvalue() {
        return dvalue;
    }

    @Override
    public String toString() {
        return "Pair{" + "value=" + value + ", dvalue=" + dvalue + '}';
    }
    
    
}
