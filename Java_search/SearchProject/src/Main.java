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
        // Scanner scanner = new Scanner(System.in);

        try (Scanner scanner = new Scanner(System.in)) {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("데이터베이스 연결완료");

            System.out.println("검색 키워드 입력: ");
            String keyword = scanner.nextLine().trim();
            // System.out.println("Entered emp_no: " + keyword);

            if (keyword.length() > 7 || !keyword.matches("\\d+")) {
                throw new IllegalArgumentException("잘못된 입력 값입니다.");
            }

            String query = "SELECT * FROM employees WHERE emp_no LIKE ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, keyword + "%");
                // System.out.println("Executing query with emp_no = " + keyword);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // boolean hasResults = false;
                    while (resultSet.next()) {
                        // hasResults = true;
                        int id = resultSet.getInt("emp_no");
                        String firstName = resultSet.getString("first_name");
                        String lastName = resultSet.getString("last_name");
                        String gender = resultSet.getString("gender");
                        String hireDate = resultSet.getString("hire_date");
                        System.out.println("사원번호: " + id + ", 이름: " + firstName + " " + lastName + ", 성별: " + gender + ", 입사일: " + hireDate);
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            logger.warning("입력 값 오류: " + e.getMessage());
        } catch (Exception e) {
            logger.severe("요청을 처리하는 동안 오류가 발생");
            e.printStackTrace();
        }
    }
}
