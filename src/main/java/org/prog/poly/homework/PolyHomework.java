package org.prog.poly.homework;

//TODO: Add interface IPhone
//TODO: Add interface method for call
//TODO: Add interface method for unlock
//TODO: Add class for Androind and iOS phones that implement IPhone

public class PolyHomework {

    public static void main(String[] args) {
        Android android = new Android();
        IOS ios = new IOS();

       usePhone(android);
       usePhone(ios);
    }

    public static void usePhone(IPhone iPhone) {
        iPhone.call();
        iPhone.unlock();
    }
}
