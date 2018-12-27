package sample.Data;
import sample.Database.DBConnection;

import java.util.ArrayList;
import java.util.Arrays;

public class Languages {
    private static ArrayList<String> tempLanguages = new ArrayList<>(Arrays.asList("svenska", "engelska", "tyska"));


    public static void validateLanguages(ArrayList<String> languages) throws MissingLanguageException {
        for(String l : languages)
            if(languageExists(l) == false)
                throw new MissingLanguageException(l);
    }

    private static boolean languageExists(String name){
        String query = "SELECT * from konstdb.språk WHERE namn = ?";

        return DBConnection.executeQuery(query,
                (s) -> {s.setString(1, name);},
                (s, t) -> {t.add(true); }).size() > 0;
    }
}

