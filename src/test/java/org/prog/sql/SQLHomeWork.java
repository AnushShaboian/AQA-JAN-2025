package org.prog.sql;

//TODO: usual Homework: Create table Addresses, where you write
// Person's Name, LastName, City Name, Street Name, Address

//TODO: usual homework + : re-write this test using BeforeSuite and AfterSuite

//TODO: Homework * : Create table Phones : PhoneId, PhoneName, PhonePrice
// - using selenium go to allo.ua, search for a phone
// - get phone name
// - get phone price
// - store phone name and phone price to DB

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.prog.base.DBConnection;
import org.prog.dto.PersonDto;
import org.prog.dto.ResultsDto;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.List;

public class SQLHomeWork extends DBConnection {

    @Test
    public void sqlWrite() throws SQLException {
        List<PersonDto> personDtos = getPersons();
        PreparedStatement preparedStatement = connection.prepareStatement
                ("INSERT INTO Addresses (FirstName, LastName, City, StreetName, StreetNumber) VALUES " +
                                "(?, ?, ?, ?, ?)");

        for (PersonDto personDto:personDtos){
            preparedStatement.setString(1, personDto.getName().getFirst());
            preparedStatement.setString(2, personDto.getName().getLast());
            preparedStatement.setString(3, personDto.getLocation().getCity());
            preparedStatement.setString(4, personDto.getLocation().getStreet().getName());
            preparedStatement.setInt(5, personDto.getLocation().getStreet().getNumber());
            try {
                preparedStatement.execute();
            }catch (SQLException exception) {
                System.out.println("SQL ERROR for person: "
                        + personDto.getName().getFirst() + " "
                        + personDto.getName().getLast());
                exception.printStackTrace();
            }

        }
    }

    private List<PersonDto> getPersons(){
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://randomuser.me/");
        requestSpecification.basePath("api/");
        requestSpecification.queryParam("noinfo");
        requestSpecification.queryParam("inc", "name, location");
        requestSpecification.queryParam("results", "5");

        Response response = requestSpecification.get();
        return response.as(ResultsDto.class).getResults();
    }


}
