/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package ch.emf.flux_jeux_videos.wrk;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mario
 */
public class WorkerTest {
    
    public WorkerTest() {
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
     * Test of serialiseObjet method, of class Worker.
     */
    @Test
    public void testSerialiseObjet() {
        System.out.println("serialiseObjet");
        String filepath = "nouveau.ser";
        Object obj = new ArrayList();
        Worker instance = new Worker();
        boolean expResult = true;
        boolean result = instance.serialiseObjet(filepath, obj);
        assertEquals(expResult, result);
        System.out.println("serialieObjet > Ok");
    }

    /**
     * Test of deserialiseObjet method, of class Worker.
     */
    @Test
    public void testDeserialiseObjet() {
        System.out.println("deserialiseObjet");
        String filepath = "";
        Worker instance = new Worker();
        Object expResult = null;
        Object result = instance.deserialiseObjet(filepath);
        assertEquals(expResult, result);
        System.out.println("serialieObjet > Ok");
    }

    /**
     * Test of readTextFile method, of class Worker.
     */
    @Test
    public void testReadTextFile() {
        System.out.println("readTextFile");
        String filepath = "";
        Worker instance = new Worker();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.readTextFile(filepath);
        assertEquals(expResult, result);
    }

    /**
     * Test of writeTextFile method, of class Worker.
     */
    @Test
    public void testWriteTextFile() {
        System.out.println("writeTextFile");
        String filepath = "";
        ArrayList<String> linesToWrite = null;
        Worker instance = new Worker();
        boolean expResult = false;
        boolean result = instance.writeTextFile(filepath, linesToWrite);
        assertEquals(expResult, result);
    }

    /**
     * Test of appendToTextFile method, of class Worker.
     */
    @Test
    public void testAppendToTextFile() {
        System.out.println("appendToTextFile");
        String filepath = "";
        String newLineContent = "";
        Worker instance = new Worker();
        boolean expResult = false;
        boolean result = instance.appendToTextFile(filepath, newLineContent);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkIfFileExists method, of class Worker.
     */
    @Test
    public void testCheckIfFileExists() {
        System.out.println("checkIfFileExists");
        String filepath = "";
        Worker instance = new Worker();
        boolean expResult = false;
        boolean result = instance.checkIfFileExists(filepath);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteFile method, of class Worker.
     */
    @Test
    public void testDeleteFile() {
        System.out.println("deleteFile");
        String filepath = "";
        Worker instance = new Worker();
        boolean expResult = false;
        boolean result = instance.deleteFile(filepath);
        assertEquals(expResult, result);
    }
    
}
