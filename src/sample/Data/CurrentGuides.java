package sample.Data;

import sample.Database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class CurrentGuides {

    private static Hashtable<String, Guide> guides = new Hashtable<>();
    public static void removeGuide(String id){guides.remove(id);}

    public static ArrayList<Guide> getAllGuides(){
        String query = "SELECT * FROM konstdb.anställd AS anst, konstdb.guide AS guide\n" +
                "WHERE anst.ID = guide.AnstID;";

        return DBConnection.executeQuery(query, (s) -> {},
                (r, g) -> {g.add(new Guide(
                        r.getString("ID"),
                        r.getString("PersonNr"),
                        r.getString("Namn"),
                        r.getString("TelefonNr"),
                        r.getString("Epost"),
                        getGuideLanguages(r.getString("ID")),
                        getGuideShows(r.getString("ID"))
                ));});
    }


    private static ArrayList<String> getGuideLanguages(String ID){
        String query = "SELECT Språk FROM konstdb.språkkunskap\n" +
                "WHERE GuideID = ?;";
        return DBConnection.executeQuery(query,
                (s) -> {s.setString(1, ID);},
                (r, g) -> {g.add(r.getString("Språk"));
        });
    }

    private static ArrayList<String> getGuideShows(String ID){
        String query = "SELECT Titel FROM konstdb.utställningskunskap, konstdb.utställning\n" +
                "WHERE Utställning = ID AND Guide = ?;";
        return DBConnection.executeQuery(query,
                (s) -> {s.setString(1, ID);},
                (r, g) -> {g.add(r.getString("Titel"));
                });
    }

    private static void updateGuideInfo(String ID, Guide dto){
        String query = "UPDATE konstdb.anställd\n" +
                "SET Namn = ?, " +
                "PersonNr = ?, " +
                "TelefonNr = ?, " +
                "Epost = ?\n" +
                "WHERE ID = ?;";
        DBConnection.executeUpdate(query, (s -> {
            s.setString(1, dto.getName());
            s.setString(2, dto.getPersonID());
            s.setString(3, dto.getPhone());
            s.setString(4, dto.getMail());
            s.setInt(5, Integer.parseInt(ID));
        }));
    }


    //Divide the given infoString into sections, then for every section we extract the appropriate data
    //Finally we update the specified guide with the new data
    public static void editGuideInfo(String ID, String infoString) throws Exception {
        Guide dto = new Guide();
        for(Section s : splitIntoSections(infoString)){
            switch (s.fieldName){
                case "name": dto.setName(parseString(s.data)); break;
                case "phone": dto.setPhone(parseString(s.data)); break;
                case "mail": dto.setMail(parseString(s.data)); break;
                case "id": dto.setPID(parseString(s.data)); break;
                case "languages": dto.setLanguages(s.data); break;
                case "shows":dto.setShows(s.data); break;
            }
        }

        updateGuideInfo(ID, dto);
    }

    //Divide the given text into the different sections
    //Used for extracting data from the Edit windows
    private static ArrayList<Section> splitIntoSections(String fullText){
        ArrayList<Section> sections = new ArrayList<Section>();

        String[] words = fullText.split(":");
        for(int i = 1; i < words.length; i++){
            String[] previousSentence = words[i-1].split("\\n+");
            String fieldName = previousSentence[previousSentence.length-1];

            ArrayList<String> fieldData = new ArrayList<String>();
            String[] dataWords = words[i].split("\\n+");
            int delta = i +1 == words.length ? 0 : 1; //If it's the last section we don't to this trick
            for(int j = 0; j < dataWords.length-delta; j++)
                if(dataWords[j].isEmpty() == false)
                    fieldData.add(dataWords[j].trim());

            sections.add(new Section(fieldName, fieldData));
        }

        return sections;
    }


    //******* Utils
    private static int parseInt(ArrayList<String> data){ return Integer.parseInt(data.get(0));}
    private static String parseString(ArrayList<String> data){return String.join(" ", data); }
    private static class Section{
        private String fieldName;
        private ArrayList<String> data;
        public Section(String fieldName, ArrayList<String> data){
            this.fieldName = fieldName.trim().toLowerCase(); this.data = data;
        }
    }
}
