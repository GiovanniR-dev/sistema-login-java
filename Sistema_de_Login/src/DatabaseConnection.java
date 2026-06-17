import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {


    private static final String URL =
            "";


    private static final String USER =
            "";


    private static final String PASS =
            "";



    public static Connection getConnection() throws SQLException {


        try {

            Class.forName("com.mysql.cj.jdbc.Driver");


        } catch (ClassNotFoundException e) {

            throw new SQLException(
                    "Driver MySQL não encontrado",
                    e
            );

        }


        return DriverManager.getConnection(
                URL,
                USER,
                PASS
        );

    }

}