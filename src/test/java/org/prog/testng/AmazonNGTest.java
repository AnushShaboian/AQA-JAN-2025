package org.prog.testng;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AmazonNGTest {

//    @BeforeTest
//    public void beforeTest() {
//        System.out.println("---------TEST----------");
//    }
//
//    @AfterTest
//    public void afterTest() {
//        System.out.println("-----------------------");
//    }

    @Test
    public void ngTest() {
        System.out.println("Hello Amazon Test!");
    }
}
