package sample.Data;
import sample.Database.DBConnection;

import java.util.ArrayList;
import java.util.Collection;

public class CurrentShows {

    public static Collection<? extends Show> getAllShows(){
        String query = "SELECT * FROM konstdb.utställning";
        return DBConnection.executeQuery(query, (s) -> {},
                (r, g) -> {g.add(new Show(
                        r.getString("ID"),
                        r.getString("Titel"),
                        r.getBigDecimal("Area"),
                        r.getTimestamp("StartTid"),
                        r.getTimestamp("SlutTid"),
                        CurrentArtObjects.getObjectsToShow(r.getString("ID"))
                ));});
    }

    public static void validateShows(ArrayList<String> shows) throws MissingShowException {
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

    public static int getShowID(String title){
        String query = "SELECT ID from konstdb.utställning WHERE Titel = ?";
        return (int) DBConnection.executeQuery(query,
                (s) -> {s.setString(1, title);},
                (s, t) -> {t.add(s.getInt("ID")); }).get(0);
    }


    public static void addNewShow(Show show){
    //    tempShows.add(show);
    }
    public static void removeShow(Show show){
    //        tempShows.remove(show);
    }

}

