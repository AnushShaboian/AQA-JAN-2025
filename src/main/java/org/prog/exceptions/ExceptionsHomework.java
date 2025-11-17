package org.prog.exceptions;


import org.prog.parent.Car;

//TODO: write a method which ca cause NullPointerException
// TODO: write try-catch-finally to catch NPE
// TODO: on exception -> print "EXCEPTION CAUGHT!"
// TODO: always print "DONE TRY-CATCH-FINALLY"
public class ExceptionsHomework {
    public static void main(String[] args) {
        Car car = new Car(null);

        try {
            int length = car.color.length();
            System.out.println("Color length: " + length);

        } catch (NullPointerException e) {
            System.out.println("Error! " + e.getMessage());
        } finally {
            System.out.println("Give car the color");
        }
    }
}
