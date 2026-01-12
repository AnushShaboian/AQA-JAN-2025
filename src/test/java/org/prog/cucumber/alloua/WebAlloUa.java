package org.prog.cucumber.alloua;

import io.cucumber.java.en.Given;
import org.prog.pages.AlloUaPages;

public class WebAlloUa {

    public static AlloUaPages alloUaPages;

    @Given("I opened the search results for the query “телефон” on the Allo.ua website")
    public void openPageWithPhones() {
        alloUaPages.loadSite();
        alloUaPages.openPhonesPage();
        alloUaPages.getPhonesList();
    }
}
