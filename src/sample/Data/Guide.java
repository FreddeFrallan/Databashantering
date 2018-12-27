package sample.Data;

import sample.Utils;

import java.util.ArrayList;
import java.util.Comparator;

public class Guide {

    private ArrayList<String> languages = new ArrayList<>();
    private ArrayList<Show> shows = new ArrayList<>();
    private String personID, name, phone, ID, mail;

    public Guide(){}
    public Guide(String ID, String personID, String name, String phone, String mail, ArrayList<String> languages, ArrayList<Show> shows){
        this.languages = languages;
        this.personID = personID;
        this.phone = phone;
        this.shows = shows;
        this.name = name;
        this.mail = mail;
        this.ID = ID;
    }


    @Override
    public String toString() {return name;}
    public String getInfoString(){
        String s = "";
        s += "ID: " + personID + "\n";
        s += "Name: " + name + "\n";
        if(phone != null) s += "Phone: " + phone + "\n";
        if(mail != null) s += "Mail: " + mail + "\n";

        s += "Languages:\n";
        for(String l : languages) s += "\t" + l + "\n";
        s += "Shows:\n";
        for(Show show : shows) s += "\t" + show.getTitle() + "\n";

        return s;
    }



    //*******  Getters & Setters
    public String getName(){return name;}
    public String getPersonID(){return personID;}
    public String getID(){return ID;}
    public String getPhone(){return phone;}
    public String getMail(){return mail;}
    public ArrayList<String> getLanguages(){return languages;}
    public ArrayList<Long> getShowIDs(){
        ArrayList<Long> t = new ArrayList<>();
        for(Show s : shows) t.add(s.getID());
        return t;
    }

    public void setName(String name){this.name = name;}
    public void setMail(String mail){this.mail = mail;}
    public void setPID(String pID){this.personID = pID;}
    public void setPhone(String phone){this.phone = phone;}
    public void setLanguages(ArrayList<String> languages) throws MissingLanguageException, DuplicateDataException {
        Languages.validateLanguages(languages);
        if(Utils.containsDuplicates(languages))
            throw new DuplicateDataException("Languages");

        this.languages = languages;
    }
    public void setShows(ArrayList<String> showTitles) throws MissingShowException, DuplicateDataException, DatabaseException {
        CurrentShows.validateShowTitles(showTitles);

        ArrayList<Show> shows = new ArrayList<>();
        for(String s : showTitles)
            shows.add(CurrentShows.getShowFromTitle(s));
        if(showTitles.size() != shows.size())
            throw new DatabaseException("Could not retreive all shows from database");

        if(Utils.containsDuplicates(shows))
            throw new DuplicateDataException("Shows");

        this.shows = shows;
    }


}
