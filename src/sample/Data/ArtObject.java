package sample.Data;

public class ArtObject {

    private String ID, title;

    public ArtObject(String ID, String title){
        this.ID = ID;
        this.title = title;
    }

    @Override
    public String toString() {return ID + " - " + title;}
    public String infoString(){return title;}
}
