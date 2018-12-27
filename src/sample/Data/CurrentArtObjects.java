package sample.Data;
import sample.Database.DBConnection;

import java.util.ArrayList;
import java.util.Arrays;

public class CurrentArtObjects {

    public static ArrayList<ArtObject> getAllObjects(){
        String query = "SELECT ID, Titel FROM konstdb.konstverk";
        return DBConnection.executeQuery(query, (s) -> {},
                (r, g) -> {g.add(new ArtObject(
                        r.getString("ID"),
                        r.getString("Titel")
                ));});
    }

    public static ArrayList<ArtObject> getObjectsToShow(String showID){
        String query = "SELECT ID, Titel FROM konstdb.konstverk, konstdb.utställningsobjekt\n" +
                "WHERE ID = KonstID AND UtID = ?";
        return DBConnection.executeQuery(query,
                (s) -> {s.setString(1, showID);},
                (r, g) -> {g.add(new ArtObject(
                        r.getString("ID"),
                        r.getString("Titel")

                ));});
    }

}

