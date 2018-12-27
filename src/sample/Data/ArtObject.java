package sample.Data;

public class ArtObject {

    private Integer ID;
    private String title;

    public ArtObject(Integer ID, String title){
        this.ID = ID;
        this.title = title;
    }

    @Override
    public String toString() {return ID + " - " + title;}
    public String infoString(){return title;}

    public Integer getID(){return ID;}
}
