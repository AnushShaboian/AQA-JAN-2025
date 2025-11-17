package org.prog.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.prog.dto.PersonDto;
import org.prog.util.Container;
import org.testng.Assert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SqlSteps {

    public static Connection connection;

    @Given("I write down the amount of people in DB as {string}")
    public void writeDownAMountOfRecordsInDB(String alias) throws SQLException {
        System.out.println("Write people to DB");
        Container.DATA_HOLDER.put(alias, countDBRecords());
    }

    @When("I store {string} in Database")
    public void storeUsersToDB(String alias) throws SQLException {
        List<PersonDto> persons = (List<PersonDto>) Container.DATA_HOLDER.get(alias);
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
    }

    @Then("I have {int} more people in DB then {string}")
    public void assertRecordsCount(int recordsCounts, String alias) throws SQLException {
        int initialCount = (int) Container.DATA_HOLDER.get(alias);
        int currentRecords = countDBRecords();
        Assert.assertEquals(currentRecords, initialCount + recordsCounts,
                "User count in DB mismatch!");
    }

    private int countDBRecords() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT count(*) from Persons p");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }
}
