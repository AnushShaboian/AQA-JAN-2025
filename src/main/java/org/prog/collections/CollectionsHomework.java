package org.prog.collections;

//TODO: write collection of cars (MAP/SET/LIST)
//TODO: WHERE: CAR CAN HAVE NO OWNER
//TODO: WHERE: CAR CAN HAVE 1 OR MORE OWNERS
//TODO: Write method that will print all owners sharing car

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Car1 -> John
 * Car2 -> John, Jane
 * Car3 -> Jane
 * Car4 ->
 * Car5 -> Sarah, Peter
 * Car6 -> Peter
 * Car7 -> Bob
 * Car8 -> Ivy
 * expected result: Peter, Sarah, John, Jane
 */

public class CollectionsHomework {

    public static void main(String[] args) {
        String driver1 = "John";
        String driver2 = "Jane";
        String driver3 = "Sarah";
        String driver4 = "Peter";
        String driver5 = "Bob";
        String driver6 = "Ivy";

        List<String> cars = new ArrayList<>();
        cars.add("Car1");
        cars.add("Car2");
        cars.add("Car3");
        cars.add("Car4");
        cars.add("Car5");
        cars.add("Car6");
        cars.add("Car7");
        cars.add("Car8");

        Map<String, List<String>> carsSharing = new HashMap<>();
        carsSharing.put(driver1, new ArrayList<>());
        carsSharing.put(driver2, new ArrayList<>());
        carsSharing.put(driver3, new ArrayList<>());
        carsSharing.put(driver4, new ArrayList<>());
        carsSharing.put(driver5, new ArrayList<>());
        carsSharing.put(driver6, new ArrayList<>());

        carsSharing.get(driver1).add(cars.get(0));
        carsSharing.get(driver1).add(cars.get(1));
        carsSharing.get(driver2).add(cars.get(1));
        carsSharing.get(driver2).add(cars.get(2));
        carsSharing.get(driver3).add(cars.get(4));
        carsSharing.get(driver4).add(cars.get(4));
        carsSharing.get(driver4).add(cars.get(5));
        carsSharing.get(driver5).add(cars.get(6));
        carsSharing.get(driver6).add(cars.get(7));

        whoSharingCar(carsSharing);
    }
        public static void whoSharingCar (Map < String, List < String >> carsSharing){
            Map<String, List<String>> carToDrive = new HashMap<>();
            for (String car : List.of("Car1", "Car2", "Car3", "Car4", "Car5", "Car6", "Car7", "Car8")) {
                List<String> driversSharing = new ArrayList<>();
                for (Map.Entry<String, List<String>> entry : carsSharing.entrySet()) {
                    String driver = entry.getKey();
                    List<String> cars = entry.getValue();

                    if (cars.contains(car)) {
                        driversSharing.add(driver);
                    }
                }
                if (driversSharing.size() > 1) {
                    System.out.println(String.join(", ", driversSharing));
                }

            }
        }
    }