import java.sql.*;
        import java.util.ArrayList;
import java.util.List;

public class DataBaseReader {

    private static final  String URL = "jdbc:mysql://127.0.0.1:3306/car_info";
    private static final String USER = "test_user";
    private static final String PASSWORD = "password";


    public static void main(String[] args) {

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // SELECT
            Statement sqlStatetment = conn.createStatement();
            ResultSet resultSet = sqlStatetment.executeQuery("SELECT * FROM car");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("model"));}



            //INSERT

            Statement sqlStatetment1 = conn.createStatement();
            String sql = "INSERT INTO car VALUES ('Ford', 'Mondeo', 'red', '2001')";
            sqlStatetment1.executeUpdate(sql);

            //UPDATE


            String upd= "update car set manufacturer = ? where model =? ";
            PreparedStatement pstmt = conn.prepareStatement(upd);
            pstmt.setString(1, "Hyundai");
            pstmt.setString(2, "Mondeo");
            pstmt.executeUpdate();

            //DELETE
            Statement sqlStatetment3 = conn.createStatement();
            String sqlDelete = "DELETE FROM car " + "WHERE model = 'Mondeo'";
            sqlStatetment3.executeUpdate(sqlDelete);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("model"));}

            resultSet.close();


        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }

    }

}
