package org.prog.cucumber.alloua;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.prog.dto.PhoneDto;
import org.prog.util.Container;

import java.sql.*;
import java.util.List;

import static org.prog.cucumber.steps.SqlSteps.connection;

public class SqlAlloUa{
    public static Connection connection;

    @When("I store the main characteristics of the first phone in DB")
    public void writePhoneToDB() throws SQLException {
        List<PhoneDto> phones = (List<PhoneDto>) Container.DATA_HOLDER.get("phones_list");
        PreparedStatement preparedStatement = connection.prepareStatement
                ("INSERT INTO Phones (PhoneName, PhonePrice) VALUES " + "(?, ?)");
        for (PhoneDto phoneDto : phones) {
            preparedStatement.setString(1, phoneDto.getName());
            preparedStatement.setInt(2, phoneDto.getPrice());
            try {
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Then("I have new record in DB")
    public void newRecordInDB () throws SQLException {
        List<PhoneDto> addedPhones = (List<PhoneDto>) Container.DATA_HOLDER.get("phones_list");
        String sql = "SELECT PhoneName, PhonePrice FROM Phones ORDER BY PhoneID DESC LIMIT 5";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("Added phones: ");
            while (rs.next()) {
                System.out.println("Phone: " + rs.getString("PhoneName") +
                        " | Price: " + rs.getInt("PhonePrice"));
            }
        }
    }
}


