package org.prog.rest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.prog.dto.ResultsDto;
import org.testng.annotations.Test;

public class ApiTests {

    @Test
    public void apiTest() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://randomuser.me/");
        requestSpecification.basePath("/api");
        requestSpecification.queryParam("inc", "gender,name,nat");
        requestSpecification.queryParam("noinfo");
        requestSpecification.queryParam("results", "3");

        Response response = requestSpecification.get();

        ResultsDto results = response.as(ResultsDto.class);
        System.out.println(results.getResults().get(0).getName().getFirst());
        System.out.println(results.getResults().get(0).getName().getLast());
//        String value = response.jsonPath().get("results[0].gender");
//        System.out.println(value);
//        Assert.assertEquals(value, "female");
//        response.prettyPrint();
//
//        ValidatableResponse validatableResponse = response.then();
//        validatableResponse.statusCode(200);
//        validatableResponse.contentType(ContentType.JSON);
//        validatableResponse.body("results[0].gender", equalTo("male"));


    }
}
