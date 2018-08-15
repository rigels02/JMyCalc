package org.rb.mycalc;

/**
 *
 * @author raitis
 */
public class Vect3D {

    //use max 16 digits after decimal point
    private static final String FLOAT_FMT = "%.16f";
    private final double x;
    private final double y;
    private final double z;

    private final double dx;
    private final double dy;
    private final double dz;

    public Vect3D(double x, double y, double z, double dx, double dy, double dz) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.dx = dx;
        this.dy = dy;
        this.dz = dz;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public double getDz() {
        return dz;
    }

    public double module(){
         return  Math.sqrt( Math.pow(x,2.) + Math.pow(y,2.) + Math.pow(z, 2.) );
    }
    
    public double module_error(){
     double a = Math.pow(x, 2.) * Math.pow(dx, 2.) +
                Math.pow(y, 2.) * Math.pow(dy, 2.) +
                Math.pow(z, 2.) * Math.pow(dz, 2.);
        double b = Math.pow(x, 2) + Math.pow(y, 2)+ Math.pow(z, 2);
        double delta = Math.sqrt(a / b);
        //sometimes double could be presented in scientific form like
        // 9.9999999E-4 etc.
        //Below we try to convert it into decimal form like: 0.0001
        Double rounded = Double.valueOf(String.format(FLOAT_FMT, delta));
       // System.out.println("org.rb.round.Vect3D.module_error()- before round: "+delta+" ,after= "+rounded);
        return rounded;
        //return RoundUtil.roundPrecision(delta1);
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.z) ^ (Double.doubleToLongBits(this.z) >>> 32));
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.dx) ^ (Double.doubleToLongBits(this.dx) >>> 32));
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.dy) ^ (Double.doubleToLongBits(this.dy) >>> 32));
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.dz) ^ (Double.doubleToLongBits(this.dz) >>> 32));
        return hash;
    }

    public boolean absEQ(Object vect){
        if (this == vect) {
            return true;
        }
        if (vect == null) {
            return false;
        }
        if (getClass() != vect.getClass()) {
            return false;
        }
        final Vect3D other = (Vect3D) vect;
    
        return this.x==other.x &&
               this.y==other.y &&
                this.z==other.z &&
                this.dx==other.dx &&
                this.dy==other.dy &&
                this.dz==other.dz ;
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
        final Vect3D other = (Vect3D) obj;
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        if (Double.doubleToLongBits(this.z) != Double.doubleToLongBits(other.z)) {
            return false;
        }
        if (Double.doubleToLongBits(this.dx) != Double.doubleToLongBits(other.dx)) {
            return false;
        }
        if (Double.doubleToLongBits(this.dy) != Double.doubleToLongBits(other.dy)) {
            return false;
        }
        if (Double.doubleToLongBits(this.dz) != Double.doubleToLongBits(other.dz)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vect3D{" + "x=" + x + ", y=" + y + ", z=" + z + ", dx=" + dx + ", dy=" + dy + ", dz=" + dz + '}';
    }
    
    
}
