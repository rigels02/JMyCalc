package org.rb.mycalc.cash;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class SimpleCash3IT {
    
    public SimpleCash3IT() {
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

    class IntCash implements Cashable{
        int iv;

        public IntCash(int iv) {
            this.iv = iv;
        }
        
        @Override
        public Pair value() {
            return new Pair(iv, iv);
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 37 * hash + this.iv;
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
            final IntCash other = (IntCash) obj;
            if (this.iv != other.iv) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return "IntCash{" + "iv=" + iv + '}';
        }
    
        
    
    }
    /**
     * Test of take method, of class SimpleCash.
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testTake() throws InterruptedException {
        System.out.println("testTake");
        IntCash[] objs 
            = new IntCash[]{
                new IntCash(0), new IntCash(1), new IntCash(2),
                new IntCash(3), new IntCash(4), new IntCash(5),
                new IntCash(6), new IntCash(7), new IntCash(8),
                new IntCash(9)
            };
        SimpleCash3 cash = new SimpleCash3();
        cash.setTest(true);
      
        for(int k=1; k<=200; k++)
        for(int i =0; i<=9; i++){
        Pair result = cash.take(objs[i]);
            Pair pair = new Pair(i, i);
        assertEquals(pair.getValue(), result.getValue(),0.0);
        assertEquals(pair.getDvalue(), result.getDvalue(),0.0);
        }
       
    }
    
    
    class CashTask implements Callable<Boolean>{

        private SimpleCash3 cash;
        
           IntCash[] objs 
            = new IntCash[]{
                new IntCash(0), new IntCash(1), new IntCash(2),
                new IntCash(3), new IntCash(4), new IntCash(5),
                new IntCash(6), new IntCash(7), new IntCash(8),
                new IntCash(9)
            };

        public CashTask(SimpleCash3 cash) {
            this.cash = cash;
        }
     
        @Override
        public Boolean call() throws Exception {
        
             System.out.println("ENTERING...  Thread: "+
                    Thread.currentThread().getName()+" : "+Thread.currentThread().getId()+
                    " Time: "+System.currentTimeMillis()+" ms"); 
                
            
        for(int i =0; i<=9; i++){
        Pair result = cash.take(objs[i]);
            Pair pair = new Pair(i, i);
        assertEquals(pair.getValue(), result.getValue(),0.0);
        assertEquals(pair.getDvalue(), result.getDvalue(),0.0);
        }
        
         System.out.println("EXITING...  Thread: "+
                    Thread.currentThread().getName()+" : "+Thread.currentThread().getId()+
                    " Time: "+System.currentTimeMillis()+" ms"); 
                
        
        return true;
        }
    
    }
    
    class CashTaskVect3DCash implements Callable<Boolean>{

        private final SimpleCash3 cash;
        
           Vect3DCashable[] objs 
            = new Vect3DCashable[]{
               new Vect3DCashable(1.,1.,1.,0.1,0.1,0.1),
        new Vect3DCashable(1.1,1.,1.,0.1,0.1,0.1),
        new Vect3DCashable(1.,1.2,1.,0.1,0.1,0.1),
        new Vect3DCashable(1.,1.,1.3,0.1,0.1,0.1),
        new Vect3DCashable(1.,1.,1.,0.1,0.1,0.1), //duplicate
        new Vect3DCashable(1.4,1.,1.,0.1,0.1,0.1),
        new Vect3DCashable(1.,1.5,1.,0.1,0.1,0.1),
        new Vect3DCashable(1.,1.,1.6,0.1,0.1,0.1),
        new Vect3DCashable(1.,1.,1.,0.1,0.1,0.1), //duplicate
        new Vect3DCashable(1.7,1.,1.5,0.1,0.1,0.1)
              
            };

           Pair[] expected =
             new Pair[]{
                new Pair(objs[0].module(),objs[0].module_error()),
                 new Pair(objs[1].module(),objs[1].module_error()),
                 new Pair(objs[2].module(),objs[2].module_error()),
                 new Pair(objs[3].module(),objs[3].module_error()),
                 new Pair(objs[4].module(),objs[4].module_error()),
                 new Pair(objs[5].module(),objs[5].module_error()),
                 new Pair(objs[6].module(),objs[6].module_error()),
                 new Pair(objs[7].module(),objs[7].module_error()),
                 new Pair(objs[8].module(),objs[8].module_error()),
                 new Pair(objs[9].module(),objs[9].module_error()) 
             };
           
        public CashTaskVect3DCash(SimpleCash3 cash) {
            this.cash = cash;
        }
     
        @Override
        public Boolean call() throws Exception {
        
             System.out.println("ENTERING...  Thread: "+
                    Thread.currentThread().getName()+" : "+Thread.currentThread().getId()+
                    " Time: "+System.currentTimeMillis()+" ms"); 
                
            
        for(int i =0; i<=9; i++){
        Pair result = cash.take(objs[i]);
           
        assertEquals(expected[i].getValue(), result.getValue(),0.0);
        assertEquals(expected[i].getDvalue(), result.getDvalue(),0.0);
        }
        
         System.out.println("EXITING...  Thread: "+
                    Thread.currentThread().getName()+" : "+Thread.currentThread().getId()+
                    " Time: "+System.currentTimeMillis()+" ms"); 
                
        
        return true;
        }
    
    }
    
    class CashTaskVect3DRandom implements Callable<Boolean>{
        static final boolean Test=false;
        private final SimpleCash3 cash;
         private final int vectNum;
        
           Vect3DCashable[] objs ;

           Pair[] expected ;

           
        private double genD(){
            double min = 0.1; double max= 10.1;
            //double random = new Random().nextDouble();
            //return min + (random*(max - min));
           return ThreadLocalRandom.current().nextDouble(min, max);
        }   
        
        private void populatedVectors(){
            for(int i=0; i< vectNum; i++){
              objs[i] = new Vect3DCashable(genD(), genD(), genD(), 0.1, 0.1, 0.1);
              expected[i] = new Pair(objs[i].module(), objs[i].module_error());
            }
        } 
        
        public CashTaskVect3DRandom(SimpleCash3 cash, int vectNum) {
            this.cash = cash;
            this.vectNum  = vectNum;
            objs = new Vect3DCashable[vectNum];
            expected = new Pair[vectNum];
            populatedVectors();
        }
     
        @Override
        public Boolean call() throws Exception {
            if(Test)
             System.out.println("ENTERING...  Thread: "+
                    Thread.currentThread().getName()+" : "+Thread.currentThread().getId()+
                    " Time: "+System.currentTimeMillis()+" ms"); 
                
            
        for(int i =0; i< vectNum; i++){
        Pair result = cash.take(objs[i]);
           
        assertEquals(expected[i].getValue(), result.getValue(),0.0);
        assertEquals(expected[i].getDvalue(), result.getDvalue(),0.0);
        }
         if(Test)
         System.out.println("EXITING...  Thread: "+
                    Thread.currentThread().getName()+" : "+Thread.currentThread().getId()+
                    " Time: "+System.currentTimeMillis()+" ms"); 
                
        
        return true;
        }
    
    }
    
    
    @Test
    public void testTakeByThreads() throws InterruptedException, ExecutionException{
        System.out.println("testTakeByThreads()");
        
        SimpleCash3 cash = new SimpleCash3();
        cash.setTest(false);
        //----//
        int threadNumber =10;
        ExecutorService service = Executors.newFixedThreadPool(threadNumber);
        List<CashTask> cashTasks= new ArrayList<>();
        List<Future<Boolean>> futuresList ;
        List<Boolean> results = new ArrayList<>();
        
        for(int i=1; i<= threadNumber; i++){
         cashTasks.add(new CashTask(cash));
        }
        futuresList = service.invokeAll(cashTasks);
        for (Future<Boolean> future : futuresList) {
            try {
                results.add(future.get());
            } catch (ExecutionException ex) {
                Logger.getLogger(SimpleCash3IT.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       service.shutdownNow();
       assertTrue(results.size()==10);
        assertTrue(cash.size()==10);
       cash.printCash();
    }

    @Test
    public void testVect3DCashByThreads() throws InterruptedException, ExecutionException{
        System.out.println("testVect3DCashByThreads()");
        
        SimpleCash3 cash = new SimpleCash3();
        cash.setTest(false);
        //----//
        int threadNumber =10;
        ExecutorService service = Executors.newFixedThreadPool(threadNumber);
        List<CashTaskVect3DCash> cashTasks= new ArrayList<>();
        List<Future<Boolean>> futuresList ;
        List<Boolean> results = new ArrayList<>();
        
        for(int i=1; i<= threadNumber; i++){
         cashTasks.add(new CashTaskVect3DCash(cash));
        }
        long start = System.currentTimeMillis();
        futuresList = service.invokeAll(cashTasks);
        for (Future<Boolean> future : futuresList) {
            try {
                results.add(future.get());
            } catch (ExecutionException ex) {
                Logger.getLogger(SimpleCash3IT.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.printf("Elapsed time= %d ms\nCash printout:\n", System.currentTimeMillis()-start);
        
       service.shutdownNow();
       cash.printCash();
       
       assertTrue(results.size()==10);
        assertTrue(cash.size()==8);
    }
    
    @Test
    public void testVect3DCashRandomByThreads() throws InterruptedException, ExecutionException{
        System.out.println("testVect3DCashRandomByThreads()");
        
        SimpleCash3 cash = new SimpleCash3(400);
        cash.setTest(false);
        //----//
        int threadNumber =10;
        int vectNumber= 20;
        ExecutorService service = Executors.newFixedThreadPool(threadNumber);
        List<CashTaskVect3DRandom> cashTasks= new ArrayList<>();
        List<Future<Boolean>> futuresList ;
        List<Boolean> results = new ArrayList<>();
        
        for(int i=1; i<= threadNumber; i++){
         cashTasks.add(new CashTaskVect3DRandom(cash, vectNumber));
        }
        long start = System.currentTimeMillis();
        futuresList = service.invokeAll(cashTasks);
        for (Future<Boolean> future : futuresList) {
            try {
                results.add(future.get());
            } catch (ExecutionException ex) {
                Logger.getLogger(SimpleCash3IT.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.printf("Elapsed time= %d ms\nCash printout:\n", System.currentTimeMillis()-start);
        
       service.shutdownNow();
       cash.printCash();
       
       assertTrue(results.size()== 10);
        assertTrue(cash.size()== threadNumber*vectNumber);
    }
    
    @Test
    public void testVect3DCashRandomByThreads_Multiple_Times() throws InterruptedException, ExecutionException{
        System.out.println("testVect3DCashRandomByThreads_Multiple_Times() ()");
        int Cycles = 10;
        long[] etimes = new long[Cycles];
        
        for(int count=0; count< Cycles; count++) {
            
        
        SimpleCash3 cash = new SimpleCash3(400);
        cash.setTest(false);
        //----//
        int threadNumber =10;
        int vectNumber= 20;
        ExecutorService service = Executors.newFixedThreadPool(threadNumber);
        List<CashTaskVect3DRandom> cashTasks= new ArrayList<>();
        List<Future<Boolean>> futuresList ;
        List<Boolean> results = new ArrayList<>();
        
        for(int i=1; i<= threadNumber; i++){
         cashTasks.add(new CashTaskVect3DRandom(cash, vectNumber));
        }
        long start = System.currentTimeMillis();
        futuresList = service.invokeAll(cashTasks);
        for (Future<Boolean> future : futuresList) {
            try {
                results.add(future.get());
            } catch (ExecutionException ex) {
                Logger.getLogger(SimpleCash3IT.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        etimes[count]= System.currentTimeMillis()-start;
        System.out.printf("Count= %d, Elapsed time= %d ms\nCash printout:\n", count+1, etimes[count] );
        
       service.shutdownNow();
      // cash.printCash();
      
       assertTrue(results.size()== 10);
        assertTrue(cash.size()== threadNumber*vectNumber);
      } //end for
        long average = 0;
        for (long lv : etimes) {
            average= average+lv;
        }
        System.out.println("Average elapsed time= " + average/Cycles+ " ms");  
    }
    
}
