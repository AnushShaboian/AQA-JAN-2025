package org.prog.testng;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GoogleNGTest {

//    @BeforeSuite
//    public void setUp() {
//        System.out.println("==== START BROWSER ====");
//    }
//
//    @AfterSuite
//    public void tearDown() {
//        System.out.println("==== STOP BROWSER ====");
//    }

    @Test
    public void ngTest() {
        System.out.println("Hello Google Test!");
    }

    @Test(dataProvider = "testParams")
    public void testWithParams(String s, int length) {
        Assert.assertEquals(s.length(), length, "String length mismatch!");
    }

    @DataProvider
    public Object[][] testParams() {
        return new Object[][]{
                {"string 1", 8},
                {"string 2", 8},
                {"string 3", 8},
                {"string 4", 8},
                {"string 5", 8},
                {"string 6", 8},
                {"string 7", 8},
                {"string 8", 8},
                {"string 9", 8},
                {"string 10", 8}
        };
    }
}
