package sample.Data;

public class MissingShowException extends Exception{
    public MissingShowException(String language){
        super(language + " is not a valid show!");
    }
}
