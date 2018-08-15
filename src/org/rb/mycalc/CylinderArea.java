package org.rb.mycalc;

import static java.lang.Math.PI;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Cylinder surface area
 * 
 * S= PI*D*(H+D/2)
 * 
 * @author raitis
 */
public class CylinderArea {

   private double D;
   private double H;
   private double dD;
   private double dH;

    public CylinderArea(double D, double H, double dD, double dH) {
        this.D = D;
        this.H = H;
        this.dD = dD;
        this.dH = dH;
    }

    public double getD() {
        return D;
    }

    public double getH() {
        return H;
    }

    public double getdD() {
        return dD;
    }

    public double getdH() {
        return dH;
    }

    public double area(){
    
        return PI*D*(H+D/2.);
    }
    
    /**
     * Area error.
     * dS = (pi*(H+D))^2*dD^2+ (pi*D)^2*dH^2;
     * @return 
     */
    public double areaError(){
       double a= pow( PI*(H + D) ,2.) * pow(dD, 2.); //(pi*(H+D))^2*dD^2
       double b= pow( PI*D , 2.) * pow(dH, 2.); // (pi*D)^2*dH^2
       return sqrt(a+b);
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.D) ^ (Double.doubleToLongBits(this.D) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.H) ^ (Double.doubleToLongBits(this.H) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.dD) ^ (Double.doubleToLongBits(this.dD) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.dH) ^ (Double.doubleToLongBits(this.dH) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CylinderArea other = (CylinderArea) obj;
        if (Double.doubleToLongBits(this.D) != Double.doubleToLongBits(other.D)) {
            return false;
        }
        if (Double.doubleToLongBits(this.H) != Double.doubleToLongBits(other.H)) {
            return false;
        }
        if (Double.doubleToLongBits(this.dD) != Double.doubleToLongBits(other.dD)) {
            return false;
        }
        if (Double.doubleToLongBits(this.dH) != Double.doubleToLongBits(other.dH)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CylinderArea{" + "D=" + D + ", H=" + H + ", dD=" + dD + ", dH=" + dH + '}';
    }

   
   
}
