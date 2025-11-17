package org.prog;

import org.prog.parent.Car;

public class Main {

    /*
    ##########################################
    #####[carOne.color]#######################
    ##########################################
    ##########[carTwo.color]##################
    ##########################################
    ##########################################
    ##########################################
    ##########################################
    ##1############10#########################
     */
    public static void main(String[] args) {
        Car carOne = new Car("red");
        Car carTwo = new Car("red");
        carOne.color = "red";
        carTwo.color = "red";
        System.out.println(carOne.equals(carTwo));
        System.out.println(carOne.hashCode());
        System.out.println(carTwo.hashCode());
    }
}
