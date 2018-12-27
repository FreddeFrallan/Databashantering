package sample.Data;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Show {

    private String ID, title;
    private BigDecimal area;
    private Timestamp startTime, endTime;
    private ArrayList<ArtObject> objects;

    public Show(String ID, String title, BigDecimal area, Timestamp start, Timestamp end, ArrayList<ArtObject> objects){
        this.ID = ID;
        this.title = title;
        this.area = area;
        this.startTime = start;
        this.endTime = end;
        this.objects = objects;
    }

    @Override
    public String toString() {return title;}
    //How we display the Guides info in the Guide & edit Guide window
    public String getInfoString(){
        String s = "";
        s += "Title: " + title + "\n";
        s += "Area: " + area + "\n";
        s += "Starts: " + startTime + "\n";
        s += "Ends: " + endTime + "\n";

        s += "Objects:\n";
        for(ArtObject o : objects)
            s += "\t" + o.infoString() + "\n";

        return s;
    }


    public String getTitle(){return title.toLowerCase();}
}
