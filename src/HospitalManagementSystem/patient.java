package HospitalManagementSystem;

import javax.sound.midi.Soundbank;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class patient {
    private Connection connection;
    private Scanner scanner;

    public patient(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }


    public void addPatient() {
        System.out.print("Enter patient Name: ");
        String patient_name = scanner.next();
        System.out.print("Enter Patient Age: ");
        int patient_age = scanner.nextInt();
        System.out.print("Enter patient Gender: ");
        String patient_gender = scanner.next();

        try {
            String query = " insert into patient(name,age,gender) VALUES(?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, patient_name);
            ps.setInt(2, patient_age);
            ps.setString(3, patient_gender);

            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("Patient admit successfully");
            } else {
                System.out.println("patient failed to admit");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void viewPatient() {
        String query = "select * from patient";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            System.out.println("Patient: ");
            System.out.println("+------------+---------------------+--------+----------+");
            System.out.println("| patient id | Name                | Age    | Gender   |");
            System.out.println("+------------+---------------------+--------+----------+");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String age = resultSet.getString("age");
                String gender = resultSet.getString("gender");
                System.out.printf("| %-10s | %-19s | %-6s | %-8s |\n",id,name,age,gender);
                System.out.println("+------------+---------------------+--------+----------+");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkPatientById(int id) {

        String query = "select * from patient WHERE id = ?";

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