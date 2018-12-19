import java.sql.*;

public class Queries {


    public static void selectAllCarBrands(Connection conn){
        String query = "SELECT DISTINCT marke FROM bil";
        try{
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            while(result.next()){
                System.out.println(result.getString("marke"));
            }
        }catch (Exception e){}
    }

    public static void selectAllBilarCertainCity(Connection conn, String city)  {
        String query =
                "SELECT fnamn, enamn, regnr, marke, farg " +
                "FROM bil, person " +
                "WHERE id=agare AND stad = ?";
        // Create a statement associated to the connection and the query.
        // The new statement is placed in the variable stmt.
        try{
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, city);
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                System.out.print("\n" + result.getString("fnamn") + " ");
                System.out.print(result.getString("enamn") + "\n");
                System.out.print(result.getString("regnr") + " ");
                System.out.print(result.getString("marke") + " ");
                System.out.println(result.getString("farg"));
            }
        }catch (Exception e){}
    }

    public static void updateCarColor(Connection conn, String regnr, String newColor)  {
        String query =
                        "UPDATE bil " +
                        "SET farg= ? WHERE regnr= ?";
        try{
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, newColor);
            stmt.setString(2, regnr);
            stmt.executeUpdate();
        }catch (Exception e){}
    }
}
