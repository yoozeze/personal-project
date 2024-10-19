import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args){
        String url = "jdbc:mariadb://localhost:3306/employees";
        // String username = "root";
        // String password = "php505";
        String username = System.getenv("DB_USERNAME");
        String password = System.getenv("DB_PASSWORD");
        Scanner scanner = new Scanner(System.in);

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");

            System.out.println("Enter search keyword (emp_no): ");
            String keyword = scanner.nextLine();

            String query = "SELECT * FROM employees WHERE first_name LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + keyword + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("emp_no");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                String hireDate = resultSet.getString("hire_date");
                System.out.println("emp_no: " + id + ", name: " + firstName + " " + lastName + ", gender: " + gender + ", hire_date: " + hireDate);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
