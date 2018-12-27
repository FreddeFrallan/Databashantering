package sample.Data;
import sample.Database.DBConnection;

import java.util.ArrayList;
import java.util.Collection;

public class CurrentShows {

    public static Collection<? extends Show> getAllShows(){
        String query = "SELECT * FROM konstdb.utställning";
        return DBConnection.executeQuery(query, (s) -> {},
                (r, g) -> {g.add(new Show(
                        r.getInt("ID"),
                        r.getString("Titel"),
                        r.getBigDecimal("Area"),
                        r.getTimestamp("StartTid"),
                        r.getTimestamp("SlutTid"),
                        CurrentArtObjects.getObjectsToShow(r.getString("ID"))
                ));});
    }

    public static Show getShowFromTitle(String title){
        String query = "SELECT * FROM konstdb.utställning WHERE Titel = ?";
        return (Show) DBConnection.executeQuery(query,
                (s) -> {s.setString(1, title);},
                (r, g) -> {g.add(new Show(
                        r.getInt("ID"),
                        r.getString("Titel"),
                        r.getBigDecimal("Area"),
                        r.getTimestamp("StartTid"),
                        r.getTimestamp("SlutTid"),
                        CurrentArtObjects.getObjectsToShow(r.getString("ID"))
                ));}).get(0);
    }

    //Could perhaps be optimized to include all of the titles in a "IN" statement
    //But this seems rather unnecessary for now
    public static void validateShowTitles(ArrayList<String> shows) throws MissingShowException {
        for(String s : shows)
            if(showExists(s) == false)
                throw new MissingShowException(s);
    }

    private static boolean showExists(String name){
        String query = "SELECT * from konstdb.utställning WHERE Titel = ?";
        return DBConnection.executeQuery(query,
                (s) -> {s.setString(1, name);},
                (s, t) -> {t.add(true); }).size() > 0;
    }


    public static long insertNewShow(Show show){
        String insertQuery = "INSERT konstdb.utställning (Titel, Area, StartTid, SlutTid)\n" +
                "VALUES (?, ?, ?, ?)";
        ArrayList keys = DBConnection.executeUpdateWithReturnKeys(insertQuery, (s -> {
            s.setString(1, show.getTitle());
            s.setBigDecimal(2, show.getArea());
            s.setTimestamp(3, show.getStartTime());
            s.setTimestamp(4, show.getEndTime());
        }));

        return keys.size() > 0 ? (long)keys.get(0) : -1;
    }

    public static void removeShow(Long ID){
        String delQuery = "DELETE FROM konstdb.utställningskunskap WHERE Utställning = ?";
        DBConnection.executeUpdate(delQuery, (s -> {s.setLong(1, ID);}));

        String delQuery2 = "DELETE FROM konstdb.guidadtur WHERE UtID = ?";
        DBConnection.executeUpdate(delQuery2, (s -> {s.setLong(1, ID);}));

        String delQuery3 = "DELETE FROM konstdb.utställningsobjekt WHERE UtID = ?";
        DBConnection.executeUpdate(delQuery3, (s -> {s.setLong(1, ID);}));

        String delQuery4 = "DELETE FROM konstdb.utställning WHERE ID = ?";
        DBConnection.executeUpdate(delQuery4, (s -> {s.setLong(1, ID);}));

        DBConnection.closeConnection();
    }
}

