package org.rb.mycalc;


import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.rb.mycalc.RoundUtil;

/**
 *
 * @author raitis
 */
public class RoundtestIT {
    
    public RoundtestIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    Double[] posv = new Double[]{0.30, 0.31, 0.35, 0.36,0.39};
    Double[] negv = new Double[]{-0.30, -0.31, -0.35, -0.36, -0.39};
    
    /**
     * Test of main method, of class roundtest.
     */
    @Test
    public void testRound() {
        System.out.println("testRound");

        int d = 1;
        for (int i = 1; i <= 4; i++) {
            List<Double> values = Arrays.asList(posv);
            for (Double value : values) {

                System.out.println("Value= " + value * d);
                System.out.println("Rounded= " + Math.round(value * d));
            }
            values = Arrays.asList(negv);
            for (Double value : values) {
                System.out.println("Value= " + value * d);
                System.out.println("Rounded= " + Math.round(value * d));
            }

            d = d * 10;
        }
    }
    
    private void testRound(int precision){
        int p= precision;
            int d = 1;
        for (int i = 1; i <= 4; i++) {
            List<Double> values = Arrays.asList(posv);
            for (Double value : values) {

                System.out.print("Value= " + value * d);
                System.out.println(", Rounded= " + RoundUtil.mround(value * d,p));
            }
            values = Arrays.asList(negv);
            for (Double value : values) {
                System.out.print("Value= " + value * d);
                System.out.println(", Rounded= " + RoundUtil.mround(value * d,p));
            }

            d = d * 10;
        }
    }
    private void testRound1(List<Double> vals, int precision){
        System.out.println("Precission = "+precision);
        for (Double val : vals) {
            System.out.print("Value= " + val);
            System.out.println(", Rounded= " + RoundUtil.mround(val , precision));
        }
    }
    
    @Test
    public void testmround(){
        System.out.println("testmround()");
        
        System.out.println("Precision = 1\n=================="); 
        testRound(1);
        
        System.out.println("Precision = 2\n=================="); 
        testRound(2);
        
        System.out.println("Precision = 3\n=================="); 
        testRound(3);
       
        
      
    }
    
    @Test
    public void testmround1(){
         
        System.out.println("testmround1()");
        
         Double[] vals = new Double[]{1., 0.12345,12.345,123.45,1234.5};
        
        System.out.println("Precision = 1\n=================="); 
        testRound1(Arrays.asList(vals),1);
        
        System.out.println("Precision = 2\n=================="); 
       testRound1(Arrays.asList(vals),2);
        
        System.out.println("Precision = 3\n=================="); 
       testRound1(Arrays.asList(vals),3);
    }
    
    @Test
    public void testmround1neg(){
         
        System.out.println("testmround1neg()");
        
         Double[] vals = new Double[]{-1., -0.12345,-12.345,-123.45,-1234.5};
        
        System.out.println("Precision = 1\n=================="); 
        testRound1(Arrays.asList(vals),1);
        
        System.out.println("Precision = 2\n=================="); 
       testRound1(Arrays.asList(vals),2);
        
        System.out.println("Precision = 3\n=================="); 
       testRound1(Arrays.asList(vals),3);
    }
    
    @Test
    public void testmround1exp(){
         
        System.out.println("testmround1exp()");
        
         Double[] vals = new Double[]{1.e-3, 0.12345e-3,12.345e-3,123.45e-3,1234.5e-3};
        
        System.out.println("Precision = 1\n=================="); 
        testRound1(Arrays.asList(vals),1);
        
        System.out.println("Precision = 2\n=================="); 
       testRound1(Arrays.asList(vals),2);
        
        System.out.println("Precision = 3\n=================="); 
       testRound1(Arrays.asList(vals),3);
    }
    
    private double hypot2(double x, double y){
       return Math.hypot(x, y);
    }
    private double hypot2Delta(double x, double y, double dx, double dy){
        double a = Math.pow(x, 2.)*Math.pow(dx, 2.) + Math.pow(y, 2.)*Math.pow(dy, 2.);
        double b = Math.pow(x, 2)+Math.pow(y, 2);
        double delta = Math.sqrt(a/b);
         return RoundUtil.roundPrecision(delta);
    }
    
    private void doHypot2Test(double x, double y, double dx, double dy){
    System.out.println(String.format("x= %f + %f, y= %f + %f",x,dx,y,dy ));
        System.out.print("Sqrt(x^2+ y^2) = "+hypot2(x, x));
        System.out.println(" ,Delta = "+hypot2Delta(x, y, dx, dy));
        System.out.println("Rounded: "+RoundUtil.mround( hypot2(x, x), hypot2Delta(x, y, dx, dy) ));
    }
    
    @Test
    public void testhypot2(){
        System.out.println("testhypot2()");
        double x=1., dx=0.1;
        double y=1., dy=0.1;
        doHypot2Test(x, y, dx, dy);
         x=1.; dx=0.01;
        y=1.; dy=0.01;
        doHypot2Test(x, y, dx, dy);
        x=1.; dx=0.001;
        y=1.; dy=0.001;
        doHypot2Test(x, y, dx, dy);
         x=1.; dx=0.002;
        y=1.; dy=0.001;
        doHypot2Test(x, y, dx, dy);
        double delta= 0.015811388300841897;
        System.out.println("digis= "+ RoundUtil.countZerrosAfterPoint(delta));
        System.out.println("Rounded delta= "+RoundUtil.roundPrecision(delta));
    }
}
