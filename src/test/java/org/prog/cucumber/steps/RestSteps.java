package org.prog.cucumber.steps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.prog.dto.PersonDto;
import org.prog.dto.ResultsDto;
import org.prog.util.Container;

import java.util.List;

public class RestSteps {

    @Given("I request {int} people from random user API as {string}")
    public void requestNewUsers(int count, String alias) {
        Container.DATA_HOLDER.put(alias, getRandomPeople(count));
    }

    private List<PersonDto> getRandomPeople(int count) {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://randomuser.me/");
        requestSpecification.basePath("/api");
        requestSpecification.queryParam("inc", "gender,name,nat");
        requestSpecification.queryParam("noinfo");
        requestSpecification.queryParam("results", count);

        Response response = requestSpecification.get();
        return response.as(ResultsDto.class).getResults();
    }
}
