package sample.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class CurrentGuides {

    private static Hashtable<String, Guide> guides = new Hashtable<>();
    public static void insertGuide(Guide g){guides.put(g.getID(), g);}
    public static void removeGuide(String id){guides.remove(id);}


    public static String getGuideInfo(String id){return guides.get(id).toString();}
    public static Guide[] getAllGuides(){
        Guide[] temp = new Guide[guides.size()];
        int i = 0;
        for(String g : guides.keySet())
            temp[i++] = guides.get(g);
        return temp;
    }


    //Divide the given infoString into sections, then for every section we extract the appropriate data
    //Finally we update the specified guide with the new data
    public static void editGuideInfo(String id, String infoString) throws Exception {
        Guide dto = new Guide();
        for(Section s : splitIntoSections(infoString)){
            switch (s.fieldName){
                case "name": dto.setName(parseString(s.data)); break;
                case "age": dto.setAge(parseInt(s.data)); break;
                case "phone": dto.setPhone(parseString(s.data)); break;
                case "languages": dto.setLanguages(s.data); break;
                case "shows":dto.setShows(s.data); break;
            }
        }
        guides.get(id).setNewParameters(dto);
    }

    //Divide the given text into the different sections
    //Used for extracting data from the Edit windows
    private static ArrayList<Section> splitIntoSections(String fullText){
        ArrayList<Section> sections = new ArrayList<Section>();

        String[] words = fullText.split(":");
        for(int i = 1; i < words.length; i++){
            String[] previousSentence = words[i-1].split("\\s+");
            String fieldName = previousSentence[previousSentence.length-1];

            ArrayList<String> fieldData = new ArrayList<String>();
            String[] dataWords = words[i].split("\\s+");
            int delta = i +1 == words.length ? 0 : 1; //If it's the last section we don't to this trick
            for(int j = 0; j < dataWords.length-delta; j++)
                if(dataWords[j].isEmpty() == false)
                    fieldData.add(dataWords[j]);

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
