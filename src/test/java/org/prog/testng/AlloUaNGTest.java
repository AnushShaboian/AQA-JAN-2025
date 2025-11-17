package org.prog.testng;


import org.testng.annotations.Test;

//TODO: create separate test-ng.xml file with this class only
//TODO: move your selenium test to this class
//TODO: Add assertion that goods name is not null
//TODO: Add assertion that goods name has > 0 length

public class AlloUaNGTest {

//    @BeforeClass
//    public void beforeAllo() {
//        System.out.println(">>>>>>>>>>ALLO.START");
//    }
//
//    @AfterClass
//    public void afterAllo() {
//        System.out.println(">>>>>>>>>>ALLO.END");
//    }
//
//    @BeforeMethod
//    public void beforeMethod() {
//        System.out.println("Test!<<<<<<<<<<<<<<<<<<<<");
//    }
//
//    @AfterMethod
//    public void afterMethod() {
//        System.out.println("Test!<<<<<<<<<<<<<<<<<<<<");
//    }

    @Test
    public void ngTest1() {
        System.out.println("Hello Allo UA Test! 5");
    }

    @Test
    public void ngTest2() {
        System.out.println("Hello Allo UA Test 1!");
    }

    @Test
    public void ngTest3() {
        System.out.println("Hello Allo UA Test 2!");
    }

    @Test
    public void ngTest4() {
        System.out.println("Hello Allo UA Test 3!");
    }

    @Test
    public void ngTest5() {
        System.out.println("Hello Allo UA Test! 4");
    }
}
