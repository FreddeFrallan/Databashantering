package sample.Data;
import java.util.ArrayList;
import java.util.Arrays;

public class CurrentShows {

    private static ArrayList<Show> tempShows = new ArrayList<>();

    public static void validateShows(ArrayList<String> shows) throws MissingShowException {
        for(String s : shows)
            if(showExists(s) == false)
                throw new MissingShowException(s);
    }
    public static boolean showExists(String title){
        for(Show s : tempShows)
            if(title.toLowerCase().equals(s.getTitle()))
                return true;
        return false;
    }


    public static void addNewShow(Show show){tempShows.add(show);}
    public static void removeShow(Show show){tempShows.remove(show); }
    public static ArrayList<Show> getAllCurrentShows(){return tempShows; }
}

