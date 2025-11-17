package org.prog.poly.homework;

public class IOS implements IPhone {

    @Override
    public void call() {
        System.out.println("IOS phone call someone");
    }

    @Override
    public void unlock() {
        System.out.println("IOS phone unlock");
    }
}
