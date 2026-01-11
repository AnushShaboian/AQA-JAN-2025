package org.prog.rest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.prog.dto.ResultsDto;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;


public class HomeworkAPI {

    @Test
    public void testApiLocation () {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://randomuser.me/");
        requestSpecification.basePath("api/");
        requestSpecification.queryParam("noinfo");
        requestSpecification.queryParam("inc", "gender, name, nat, location");

        Response response = requestSpecification.get();
        response.prettyPrint();

        ValidatableResponse validatableResponse = response.then();
        validatableResponse.body("results[0].location.city", notNullValue());

        String value = response.jsonPath().get("results[0].location.city");
        System.out.println(value);
        Assert.assertNotNull(value);

        ResultsDto results = response.as(ResultsDto.class);
        System.out.println(results.getResults().get(0).getLocation().getCity());
        Assert.assertNotNull(results.getResults().get(0).getLocation().getCity());
    }
}
