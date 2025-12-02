package org.prog.collections;

import org.prog.parent.Car;

import java.util.*;

// 1. bind one person to a single car
// 2. bind one person to multiple cars
// 3. add logic to count cars for specific person

//TODO: Make cars unique for each drive

public class MapsPractice {

    public static void main(String[] args) {
        String driver1 = "John";
        String driver2 = "Jane";
        String driver3 = "Joe";

        Map<String, List<Car>> ownedCars = new HashMap<>();
        ownedCars.put(driver1, new ArrayList<>());
        ownedCars.put(driver2, new ArrayList<>());
        ownedCars.put(driver3, new ArrayList<>());

        Map<String, Set<String>> ownedColors = new HashMap<>();
        ownedColors.put(driver1, new HashSet<>());
        ownedColors.put(driver2, new HashSet<>());
        ownedColors.put(driver3, new HashSet<>());

        addCar(driver1, new Car("Red"), ownedCars, ownedColors);
        addCar(driver1, new Car("Blue"), ownedCars, ownedColors);
        addCar(driver1, new Car("Black"), ownedCars, ownedColors);
        addCar(driver1, new Car("Purple"), ownedCars, ownedColors);
        addCar(driver1, new Car("Red"), ownedCars, ownedColors);

        addCar(driver2, new Car("Blue"), ownedCars, ownedColors);
        addCar(driver2, new Car("Yellow"), ownedCars, ownedColors);

        addCar(driver3, new Car("White"), ownedCars, ownedColors);
    }
        public static void addCar (String driver, Car car,
                Map < String, List < Car >> ownedCars,
                Map < String, Set < String >> ownedColors){

            String color = car.color;

            if (ownedColors.get(driver).add(color)) {
                ownedCars.get(driver).add(car);
            } else {
                System.out.println("Driver " + driver + " already has a car with color " + color);
            }

        }
    }