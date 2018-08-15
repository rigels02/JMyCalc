package org.rb.mycalc;

/**
 *
 * @author raitis
 */
public class RoundUtil {

    /**
     *
     * @param value
     * @param precision digits number after point
     * @return
     */
    public static double mround(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    /**
     *
     * @param value
     * @param precision value's precision like 0.1, 0.02, etc.
     * @return
     */
    public static double mround(double value, double precision) {

        //count digits after point
        String[] decParts = Double.toString(precision).split("\\.");
        if (decParts.length < 2) {
            throw new IllegalArgumentException("Illegal precision: no point found!");
        }
        int numOfDig = decParts[1].length();
        return mround(value, numOfDig);
    }

    /**
     * Counts sequancial zeros after the decimal point.
     * Must be in float format like : 0.0001 (Not Scientific 9.9999E-3)
     * @param precision
     * @return 
     */
    public static int countZerrosAfterPoint(double precision) {
        String[] decParts = Double.toString(precision).split("\\.");
        if (decParts.length < 2) {
            throw new IllegalArgumentException("Illegal precision: no point found!");
        }
        int counter = 0;
        for (char chr : decParts[1].toCharArray()) {
            if (chr == '0') {
                counter++;
            } else {
                break;
            }
        }
        return counter;
    }

    /**
     * Precision must be in float format.
     * 
     * @see org.rb.RoundUtil.countZerrosAfterPoint
     * @param precision
     * @return 
     */
    public static double roundPrecision(double precision) {
        int precision_digits_count = countZerrosAfterPoint(precision) + 1;
        return mround(precision, precision_digits_count);
    }

    /**
     * Calculate error value for SQRT(x^2+y^2) if dx, dy available. 
     * @param x
     * @param y
     * @param dx x error
     * @param dy y error
     * @return 
     */
    public double hypot2Delta(double x, double y, double dx, double dy) {
        double a = Math.pow(x, 2.) * Math.pow(dx, 2.) + Math.pow(y, 2.) * Math.pow(dy, 2.);
        double b = Math.pow(x, 2) + Math.pow(y, 2);
        double delta = Math.sqrt(a / b);
        return RoundUtil.roundPrecision(delta);
    }

    /**
     * Calculate value for SQRT(x^2+y^2+z^2)
     * @param x
     * @param y
     * @param z
     * @return SQRT(x^2+y^2+z^2)
     */
    public double hypot3(double x, double y, double z){
      return  Math.sqrt( Math.pow(x,2.) + Math.pow(y,2.) + Math.pow(z, 2.) );
    }
    
    /**
     * Calculate error value for SQRT(x^2+y^2+z^2) if dx, dy, dz available. 
     * @param x
     * @param y
     * @param z
     * @param dx x error
     * @param dy y error
     * @param dz z error
     * @return error value
     */
    public double hypot3Delta(double x, double y, double z, double dx, double dy, double dz) {
        double a = Math.pow(x, 2.) * Math.pow(dx, 2.) +
                Math.pow(y, 2.) * Math.pow(dy, 2.) +
                Math.pow(z, 2.) * Math.pow(dz, 2.);
        double b = Math.pow(x, 2) + Math.pow(y, 2)+ Math.pow(z, 2);
        double delta = Math.sqrt(a / b);
        return RoundUtil.roundPrecision(delta);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("round.RoundUtil.main(): Run TESTS!...");
    }

}
