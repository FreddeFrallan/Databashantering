package sample.Data;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Show {

    private long ID;
    private String title;
    private BigDecimal area;
    private Timestamp startTime, endTime;
    private ArrayList<ArtObject> objects;

    public Show(Integer ID, String title, BigDecimal area, Timestamp start, Timestamp end, ArrayList<ArtObject> objects){
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

    public long getID(){return ID;}
    public String getTitle(){return title.toLowerCase();}
    public BigDecimal getArea(){return area;}
    public Timestamp getStartTime(){return startTime;}
    public Timestamp getEndTime(){return endTime;}

    public void setID(long ID){this.ID = ID;}

    public boolean equals(Object o){
        if((o instanceof Show) == false)
            return false;
        return ((Show)o).getID() == ID;
    }
}
