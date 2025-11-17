package org.prog.sql;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.prog.dto.PersonDto;
import org.prog.dto.ResultsDto;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.List;

public class SqlTest {

    @Test
    public void sqlRead() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "user", "password");

        PreparedStatement preparedStatement = connection.prepareStatement("select * from Persons where Gender= ?");
        preparedStatement.setString(1, "female");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("FirstName") + " " + resultSet.getString("LastName"));
        }
        connection.close();
    }

    @Test
    public void sqlWrite() throws SQLException, ClassNotFoundException {
        List<PersonDto> persons = getRandomPeople();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "user", "password");

        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Persons (FirstName, LastName, Gender, Title, Nat) VALUES " +
                        "(?, ?, ?, ?, ?)");

        for (PersonDto person : persons) {
            preparedStatement.setString(1, person.getName().getFirst());
            preparedStatement.setString(2, person.getName().getLast());
            preparedStatement.setString(3, person.getGender());
            preparedStatement.setString(4, person.getName().getTitle());
            preparedStatement.setString(5, person.getNat());
            try {
                preparedStatement.execute();
            } catch (SQLException e) {
                System.out.println("Failed to store in DB : " + person.getName().getFirst() + " " + person.getName().getLast());
            }
        }
        connection.close();
    }

    private List<PersonDto> getRandomPeople() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://randomuser.me/");
        requestSpecification.basePath("/api");
        requestSpecification.queryParam("inc", "gender,name,nat");
        requestSpecification.queryParam("noinfo");
        requestSpecification.queryParam("results", "3");

        Response response = requestSpecification.get();
        return response.as(ResultsDto.class).getResults();
    }
}
