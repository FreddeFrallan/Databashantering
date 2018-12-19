import java.sql.*;

/* This program is an example used to illustrate how JDBC works.
 ** It uses the JDBC driver for MySQL.
 **
 ** This program was originally written by nikos dimitrakas
 ** on 2007-08-31 for use in the basic database courses at DSV.
 **
 ** There is no error management in this program.
 ** Instead an exception is thrown. Ideally all exceptions
 ** should be caught and managed appropriately. But this
 ** program's goal is only to illustrate the basic JDBC classes.
 **
 ** Last modified by nikos on 2015-10-07
 */


public class DBConnection {

    // DB connection variable
    private static Connection conn;
    // DB access variables
    private String URL = "jdbc:mysql:///testdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String userID = "root";
    private String password = "Sneriko123";

    // method for establishing a DB connection
    public void connect(){
        try{
            System.out.println("-------- Running connect() ---------");
            // register the driver with DriverManager
            Class.forName(driver);
            //create a connection to the database
            conn = DriverManager.getConnection(URL, userID, password);
            // Set the auto commit of the connection to false.
            // An explicit commit will be required in order to accept
            // any changes done to the DB through this connection.
            conn.setAutoCommit(false);
            //Some logging
            System.out.println("Connected to " + URL + " using "+ driver);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void closeConnection() throws SQLException {
        conn.commit();// Commit the changes made to the database through this connection.
        conn.close();// Close the connection.
    }


    public Connection getConn(){return conn;}
}
