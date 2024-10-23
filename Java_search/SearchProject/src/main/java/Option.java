package main.java;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.logging.Logger;

public class Option {
    private static final Logger logger = Logger.getLogger(Option.class.getName());
    public static void main(String[] args) {
        option();
    }
    public static void option(){
        String url = "jdbc:mariadb://localhost:3306/employees";
        String username = System.getenv("DB_USERNAME");
        String password = System.getenv("DB_PASSWORD");

        try (Scanner scanner = new Scanner(System.in)) {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("데이터베이스 연결 완료!");

            System.out.println("검색 옵션 선택");
            System.out.println("1: 정확한 사번 입력");
            System.out.println("2: 부분 사번 입력");
            int option = scanner.nextInt();
            scanner.nextLine();

            String query;
            PreparedStatement preparedStatement;

            if (option == 1) {
                System.out.println("정확한 사번 입력: ");
                String exactKeyword = scanner.nextLine();
                query = "SELECT * FROM employees WHERE emp_no = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, Integer.parseInt(exactKeyword));
            } else if (option == 2) {
                System.out.println("부분 사번 입력: ");
                String partialKeyword = scanner.nextLine();
                query = "SELECT * FROM employees WHERE emp_no LIKE ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, partialKeyword + "%");
            } else {
                System.out.println("잘못된 옵션");
                return;
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            boolean hasResults = false;
            while (resultSet.next()) {
                hasResults = true;
                int id = resultSet.getInt("emp_no");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                String hireDate = resultSet.getString("hire_date");
                System.out.println("emp_no: " + id + ", name: " + firstName + " " + lastName + ", gender: " + gender + ", hire_date: " + hireDate);
            }

            if (!hasResults) {
                System.out.println("결과를 찾을 수 없습니다.");
            }

            connection.close();
        } catch (Exception e) {
            logger.severe("오류 발생" + e.getMessage());
            e.printStackTrace();
        }
    }

}
