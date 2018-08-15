package org.rb.mycalc;

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
public class CylinderAreaIT {
    
    public CylinderAreaIT() {
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
     * Test of area method, of class CylinderArea.
     */
    @Test
    public void testArea() {
        System.out.println("area");
        double[] dx = new double[]{0., 0.1, 0.01, 0.001,0.0001};
        double[] dy = new double[]{0., 0.1, 0.01, 0.001,0.0001};
        double expResult = 753.9822368615503;
        
        for(int i=0; i< dx.length; i++){
            
        CylinderArea instance = new CylinderArea(20., 2., dx[i], dy[i]);
        double error= instance.areaError();
        double result = instance.area();
            System.out.println("Area()= "+result+" :dS= "+error);
        assertEquals(expResult, result, error);
        
        }
        System.out.println("---Done---");
       
    }

    
}
