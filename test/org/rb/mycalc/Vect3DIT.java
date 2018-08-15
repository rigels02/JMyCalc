package org.rb.mycalc;

import org.rb.mycalc.Vect3D;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author raitis
 */
public class Vect3DIT {
    
    public Vect3DIT() {
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

    
    /**
     * Test of module method, of class Vect3D.
     */
    @Test
    public void testModule() {
        System.out.println("module");
        Vect3D vect1 = new Vect3D(1., 1., 1., 0, 0, 0);
        double expResult = Math.sqrt(3.);
        double result = vect1.module();
        assertEquals(expResult, result, 0.0);
       //-----
       double x=1.001, y=1.0003, z=1.0004, dx=0.001, dy=0.001, dz=0.001;
       vect1 = new Vect3D(x,y,z,dx,dy,dz);
       
       result = vect1.module();
        double delta = vect1.module_error();
        System.out.println("Expected = "+expResult+" ,vect1.module= "+result+" ,delta= "+delta);
        assertEquals(expResult, result, delta);
        //----
       x=1.0005; y=1.0003; z=1.0004; dx=0.001; dy=0.001; dz=0.001;
       vect1 = new Vect3D(x,y,z,dx,dy,dz);
       
       result = vect1.module();
       delta = vect1.module_error();
        System.out.println("Expected = "+expResult+" ,vect1.module= "+result+" ,delta= "+delta);
        assertEquals(expResult, result, delta);
         //----
       x=1.0005; y=2.0003; z=3.0004; dx=0.001; dy=0.001; dz=0.001;
       expResult = Math.sqrt(14.);
       vect1 = new Vect3D(x,y,z,dx,dy,dz);
       
       result = vect1.module();
       delta = vect1.module_error();
        System.out.println("Expected = "+expResult+" ,vect1.module= "+result+" ,delta= "+delta);
        assertEquals(expResult, result, delta);
        
        //----
       x=100.0005; y=20.0003; z=300.0004; dx=0.001; dy=0.001; dz=0.001;
       expResult = Math.sqrt(10000.+400.+90000.);
       vect1 = new Vect3D(x,y,z,dx,dy,dz);
       
       result = vect1.module();
       delta = vect1.module_error();
        System.out.println("Expected = "+expResult+" ,vect1.module= "+result+" ,delta= "+delta);
        assertEquals(expResult, result, delta);
        //----
       x=300.0005; y=300.0003; z=300.0004; dx=0.001; dy=0.001; dz=0.001;
       expResult = Math.sqrt(90000.+90000.+90000.);
       vect1 = new Vect3D(x,y,z,dx,dy,dz);
       
       result = vect1.module();
       delta = vect1.module_error();
        System.out.println("Expected = "+expResult+" ,vect1.module= "+result+" ,delta= "+delta);
        assertEquals(expResult, result, delta);
    }

   
    @Test 
    public void testVect3D_EQ(){
        System.out.println("testVect3D_EQ()");
        
        double x=11.02,y=2.34,z=30.001,dx=0.001,dy=0.001,dz=0.001;
        double x1=11.02,y1=2.34,z1=30.001,dx1=0.001,dy1=0.001,dz1=0.001;
        double x2=11.02,y2=2.35,z2=30.001,dx2=0.001,dy2=0.001,dz2=0.001;
        double x3=11.02,y3=2.34,z3=30.001,dx3=0.001,dy3=0.002,dz3=0.001;
        
        Vect3D vect = new Vect3D(x, y, z, dx, dy, dz);
        Vect3D vect1 = new Vect3D(x1, y1, z1, dx1, dy1, dz1);
        Vect3D vect2 = new Vect3D(x2, y2, z2, dx2, dy2, dz2);
        Vect3D vect3 = new Vect3D(x3, y3, z3, dx3, dy3, dz3);
        assertTrue(vect1.equals(vect));
        
        assertTrue( !vect1.equals(vect2) );
        
        assertTrue( !vect.equals(vect3));
        Vect3D vectA = new Vect3D(1., 1., 1., 0.001, 0.001, 0.001);
        Vect3D vectB = new Vect3D(1., 1., 1., 0.001, 0.001, 0.001);
        assertTrue(vectA.equals(vectB));
        
        vectA = new Vect3D(1., 1., 1., 0.001,  0.001, 0.001);
        vectB = new Vect3D(1., 1., 1., 0.0001, 0.001, 0.001);
        assertTrue(!vectA.equals(vectB));
        
        vectA = new Vect3D(1., 1., 1.,     0.001, 0.001, 0.001);
        vectB = new Vect3D(1., 1., 1.0001, 0.001, 0.001, 0.001);
        assertTrue( !vectA.equals(vectB));
        
        vectA = new Vect3D(1000.00001, 1., 1., 0.001, 0.001, 0.001);
        vectB = new Vect3D(1000.00001, 1., 1., 0.001, 0.001, 0.001);
        assertTrue( vectA.equals(vectB));
        
        vectA = new Vect3D(1000.00001, 1., 1., 0.001, 0.001, 0.001);
        vectB = new Vect3D(1000.00002, 1., 1., 0.001, 0.001, 0.001);
        assertTrue( !vectA.equals(vectB));
        
    }
    
    @Test
    public void testabsEQ(){
            System.out.println("Vect3DIT.testabsEQ()");
       Vect3D vect1= new Vect3D(1., 1., 1., 0.001, 0.001, 0.001);
       Vect3D vect2 = new Vect3D(1., 1., 1., 0.001, 0.001, 0.001);    
        assertTrue(vect1.absEQ(vect2));
        
       vect1= new Vect3D(1., 1., 1., 0.001, 0.001, 0.001);
       vect2 = new Vect3D(1., 1., 1., 0.0001, 0.001, 0.001);   
       assertTrue( !vect1.absEQ(vect2) );
       
       vect1= new Vect3D(1., 1., 1., 0.001, 0.001, 0.001);
       vect2 = new Vect3D(1., 1., 1.0001, 0.001, 0.001, 0.001);   
       assertTrue( !vect1.absEQ(vect2) );
       
       vect1= new Vect3D(1000.00001, 1., 1., 0.001, 0.001, 0.001);
       vect2= new Vect3D(1000.00001, 1., 1., 0.001, 0.001, 0.001);   
       assertTrue( vect1.absEQ(vect2) );
       
       vect1= new Vect3D(1000.00001, 1., 1., 0.001, 0.001, 0.001);
       vect2= new Vect3D(1000.00002, 1., 1., 0.001, 0.001, 0.001);   
       assertTrue( !vect1.absEQ(vect2) );
       
        vect1= new Vect3D(1000.0000001, 1., 1., 0.001, 0.001, 0.001);
        vect2= new Vect3D(1000.0000001, 1., 1., 0.001, 0.001, 0.001);   
       assertTrue( vect1.absEQ(vect2) );
       
       vect1= new Vect3D(1000.0000001,  1., 1., 0.001, 0.001, 0.001);
       vect2= new Vect3D(1000.00000005, 1., 1., 0.001, 0.001, 0.001);   
       assertTrue( !vect1.absEQ(vect2) );
    }
}
