package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
    private Connection connection;

    public Doctor(Connection connection) {
        this.connection = connection;
    }

    public void viewDoctors() {
        String query = "select * from doctors";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            System.out.println("Doctors: ");
            System.out.println("+------------+------------------+------------------+");
            System.out.println("| Doctor id  | Name             | Specialization   |");
            System.out.println("+------------+------------------+------------------+");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                System.out.printf("| %-10s |%-17s | %-16s |\n", id, name, specialization);
                System.out.println("+------------+------------------+------------------+");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkDoctorById(int id) {

        String query = "select * from Doctors WHERE id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
} 