import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {


    private static final String URL =
            "SEU_URL";


    private static final String USER =
            "SEU_USUARIO";


    private static final String PASS =
            "SUA_SENHA";



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
