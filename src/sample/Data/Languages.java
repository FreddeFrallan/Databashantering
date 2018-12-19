package sample.Data;
import java.util.ArrayList;
import java.util.Arrays;

public class Languages {
    private static ArrayList<String> tempLanguages = new ArrayList<>(Arrays.asList("svenska", "engelska", "tyska"));


    public static void validateLanguages(ArrayList<String> languages) throws MissingLanguageException {
        for(String l : languages)
            if(languageExists(l) == false)
                throw new MissingLanguageException(l);
    }
    public static boolean languageExists(String language){
        for(String l : tempLanguages)
            if(language.toLowerCase().equals(l))
                return true;
        return false;
    }
}

