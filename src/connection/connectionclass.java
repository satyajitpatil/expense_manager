package connection;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author satyajit_patil
 */
public class connectionclass {
    public Connection connection;
    public Connection getConnection() throws SQLException{
        
        String db="kiraana";
        String username="root";
        String password="sat@269854#";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/"+db,username,password);
        } catch (ClassNotFoundException ex) {
            System.out.println("connection failed");
        }
        return connection;
    }
}
