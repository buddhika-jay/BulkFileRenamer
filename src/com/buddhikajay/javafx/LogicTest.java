package com.buddhikajay.javafx;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LogicTest {

    private Logic logic;
    @Before
    public void setUp() throws Exception {
        logic = new Logic();

    }

    @Test
    public void testRename() throws FileNotFoundException {
        boolean result = logic.rename("C://Users//Buddhika//Desktop//Temp//New folder//p.txt","C://Users//Buddhika//Desktop//Temp//New folder//Renamed.txt");
        assertEquals(true, result);
    }
    @Test
    public void testPrepareRename() throws IOException{

        File f1 = new File("C://Users//Buddhika//Desktop//Temp//New folder//x.txt");
        File f2 = new File("C://Users//Buddhika//Desktop//Temp//New folder//y.txt");
        File f3 = new File("C://Users//Buddhika//Desktop//Temp//New folder//z.txt");

        List<File> fileList = new ArrayList<File>(3);
        fileList.add(f1);
        fileList.add(f2);
        fileList.add(f3);

        assertEquals(logic.prepareRename(fileList, 1, "Testing"),3);

    }
    @Test
    public void testGetOldName(){
        File file = new File("C://Users//Buddhika//Desktop//Temp//New folder//x.txt");
        assertEquals("C://Users//Buddhika//Desktop//Temp//New folder//x.txt", logic.getOldName(file));
    }

    @Test
    public void testGetNewName(){
        assertEquals("C://Users//Buddhika//Desktop//Temp//New folder//Hello 1.txt",logic.getNewName("C://Users//Buddhika//Desktop//Temp//New folder//x.txt","Hello",1));
    }

    @After
    public void tearDown() throws Exception {

    }
}