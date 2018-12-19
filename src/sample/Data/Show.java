package sample.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Show {

    private String title;
    private ArrayList<ArtObject> objects;

    public Show(String title, ArrayList<ArtObject> objects){
        this.title = title;
        this.objects = objects;
    }

    @Override
    public String toString() {return title;}
    //How we display the Guides info in the Guide & edit Guide window
    public String getInfoString(){
        String s = "";
        s += "Title: " + title + "\n";

        s += "Objects:\n";
        for(ArtObject o : objects)
            s += "\t" + o.toString() + "\n";

        return s;
    }


    public String getTitle(){return title.toLowerCase();}
}
