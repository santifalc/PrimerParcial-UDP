package py.una.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Santi
 */
public class Bd {

    private static final String url = "jdbc:postgresql://localhost:5432/udp";
    private static final String user = "postgres";
    private static final String password = "postgres";
 
    /**
     * @return objeto Connection 
     */
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    
}
