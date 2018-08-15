package org.rb.mycalc.cash;

/**
 *
 * @author raitis
 */
public interface Cashable {

    Pair value();
    
    @Override
    public int hashCode();
    
    @Override
    boolean equals(Object obj);
}
