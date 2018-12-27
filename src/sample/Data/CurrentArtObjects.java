package sample.Data;
import sample.Database.DBConnection;

import java.util.ArrayList;
import java.util.Arrays;

public class CurrentArtObjects {

    public static ArrayList<ArtObject> getAllObjects(){
        String query = "SELECT ID, Titel FROM konstdb.konstverk";
        return DBConnection.executeQuery(query, (s) -> {},
                (r, g) -> {g.add(new ArtObject(
                        r.getInt("ID"),
                        r.getString("Titel")
                ));});
    }

    public static ArrayList<ArtObject> getObjectsToShow(String showID){
        String query = "SELECT ID, Titel FROM konstdb.konstverk, konstdb.utställningsobjekt\n" +
                "WHERE ID = KonstID AND UtID = ?";
        return DBConnection.executeQuery(query,
                (s) -> {s.setString(1, showID);},
                (r, g) -> {g.add(new ArtObject(
                        r.getInt("ID"),
                        r.getString("Titel")
                ));});
    }

    public static void insertNewObjectsToShow(Show show, ArrayList<ArtObject> objects){
        String query = "INSERT konstdb.utställningsobjekt (UtID, KonstID)" +
                "VALUES (?, ?)";
        for(ArtObject o : objects){
            DBConnection.executeUpdate(query, (s -> {
                s.setLong(1, show.getID());
                s.setInt(2, o.getID());
            } ));
        }
    }
}

