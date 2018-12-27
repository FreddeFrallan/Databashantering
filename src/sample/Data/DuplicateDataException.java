package sample.Data;

public class DuplicateDataException extends Exception{
    public DuplicateDataException(String language){
        super("Duplicate data in field: " + language);
    }
}
