package sample.Database;
import com.mysql.cj.protocol.Resultset;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;

public class DBConnection {

    // DB connection variable
    private static Connection conn;
    // DB access variables
    private static String URL = "jdbc:mysql:///konstdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String userID = "root";
    private static String password = "";


    // method for establishing a DB connection
    private static void connect(){
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

    public static void closeConnection(){
        if(conn == null)
            return;
        try {
            conn.commit();// Commit the changes made to the database through this connection.
            conn.close();// Close the connection.
            conn = null;
        }catch (Exception e){}
    }


    public static Connection getConn(){
        if(conn == null)
            connect();
        return conn;
    }


    public static ArrayList executeQuery(String qString, PrepStatements prep, ResultStatement extract){
        try {
            ArrayList temp = new ArrayList();
            PreparedStatement statement = DBConnection.getConn().prepareStatement(qString);
            prep.apply(statement);

            ResultSet result = statement.executeQuery();
            while (result.next())
                extract.apply(result, temp);

            return temp;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ArrayList();
        }
    }

    public static void executeUpdate(String qString, PrepStatements prep){
        try {
            PreparedStatement statement = DBConnection.getConn().prepareStatement(qString);
            prep.apply(statement);
            statement.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList executeUpdateWithReturnKeys(String qString, PrepStatements prep){
        ArrayList temp = new ArrayList();
        try {
            PreparedStatement statement = DBConnection.getConn().prepareStatement(qString, Statement.RETURN_GENERATED_KEYS);
            prep.apply(statement);
            statement.executeUpdate();

            var keys = statement.getGeneratedKeys();
            while(keys.next())
                temp.add(keys.getLong(1));
            System.out.println("Keeys:" + temp.size());
        }catch (Exception e){System.out.println(e.getMessage());}
        return temp;
    }

}

