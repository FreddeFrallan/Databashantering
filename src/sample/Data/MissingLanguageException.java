package sample.Data;

public class MissingLanguageException extends Exception{
    public MissingLanguageException(String language){
        super(language + " is not a valid language!");
    }
}
