package org.prog.cucumber.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;

public class DemoSteps {

    @Given("I open web site")
    public void printStuff() {
        System.out.println("Web site is opened");
    }

    @Given("I press register new user")
    public void userReg() {
        System.out.println("User registration is opened");
    }

    @Given("I set username to {string}")
    public void userName(String userName) {
        System.out.println("username is " + userName);
    }

    @Given("I set password to {string}")
    public void password(String password) {
        System.out.println("password is " + password);
    }

    @Given("Demo data table list")
    public void dataTableDemo(DataTable dataTable) {
        for (String s : dataTable.asList()) {
            System.out.println(s);
        }
    }
}
