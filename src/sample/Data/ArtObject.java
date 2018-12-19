package sample.Data;

public class ArtObject {

    private String title;

    public ArtObject(String title){
        this.title = title;
    }

    @Override
    public String toString() {return title;}
}
