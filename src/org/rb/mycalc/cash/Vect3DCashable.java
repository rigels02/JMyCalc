package org.rb.mycalc.cash;

import org.rb.mycalc.Vect3D;

/**
 *
 * @author raitis
 */
public class Vect3DCashable extends Vect3D implements Cashable{

    public Vect3DCashable(double x, double y, double z, double dx, double dy, double dz) {
        super(x, y, z, dx, dy, dz);
    }

    
    
    @Override
    public Pair value() {
        return new Pair(this.module(), this.module_error());
    }
    
    
}
