package org.prog.junit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

public class GoogleTests {

    @BeforeAll
    public static void setUp() {
        System.out.println("Open browser.");
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("Close browser");
    }

    @BeforeEach
    public void beforeTest() {
        System.out.println("Go to google.com");
        System.out.println("Accept cookies if present");
    }

    @AfterEach
    public void afterTest() {
        System.out.println("=========================");
    }

    @ParameterizedTest
    @MethodSource("celebritiesAndOutcomes")
    public void someTest2(String celebrityName, String expectedWebSite) {
        System.out.println("Lucky search for " + celebrityName);
        System.out.println("Confirm " + expectedWebSite + " page is loaded");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Ben Affleck", "Margot Robie", "Nikolas Cage"})
    public void paramTest(String celebrityName) {
        System.out.println("Search for " + celebrityName);
        System.out.println("Confirm at lest 5 results found");
    }

    @ParameterizedTest
    @ValueSource(ints = {817263, 54320, 7655, 6878912})
    public void testSmth(int i) {
        boolean result = (i % 2) == 0;
        String assertionMessage = "Expected number " + i + " to be divided by 2 without rest";
        Assertions.assertTrue(result, assertionMessage);
    }

    private static Stream<Arguments> celebritiesAndOutcomes() {
        return Stream.of(
                Arguments.of("Ben Affleck", "Wiki"),
                Arguments.of("Ben Affleck", "Wiki"),
                Arguments.of("Margot Robbie", "Wiki"),
                Arguments.of("Nikolas Cage", "IMDB")
        );
    }
}
