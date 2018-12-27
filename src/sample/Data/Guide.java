package sample.Data;

import java.util.ArrayList;

public class Guide {

    private ArrayList<String> languages = new ArrayList<>();
    private ArrayList<String> shows = new ArrayList<>();
    private String personID, name, phone, ID, mail;

    public Guide(){}
    public Guide(String ID, String personID, String name, String phone, String mail, ArrayList<String> languages, ArrayList<String> shows){
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
    //How we display the Guides info in the Guide & edit Guide window
    public String getInfoString(){
        String s = "";
        s += "ID: " + personID + "\n";
        s += "Name: " + name + "\n";
        if(phone != null) s += "Phone: " + phone + "\n";
        if(mail != null) s += "Mail: " + mail + "\n";

        s += "Languages:\n";
        for(String l : languages) s += "\t" + l + "\n";
        s += "Shows:\n";
        for(String l : shows) s += "\t" + l + "\n";

        return s;
    }



    //*******  Getters & Setters
    public String getName(){return name;}
    public String getPersonID(){return personID;}
    public String getID(){return ID;}
    public String getPhone(){return phone;}
    public String getMail(){return mail;}
    public ArrayList<String> getLanguages(){return languages;}
    public ArrayList<Integer> getShowIDs(){
        ArrayList<Integer> t = new ArrayList<>();
        for(String show : shows)
            t.add(CurrentShows.getShowID(show));
        return t;
    }

    public void setName(String name){this.name = name;}
    public void setMail(String mail){this.mail = mail;}
    public void setPID(String pID){this.personID = pID;}
    public void setPhone(String phone){this.phone = phone;}
    public void setLanguages(ArrayList<String> languages) throws MissingLanguageException {
        Languages.validateLanguages(languages);
        this.languages = languages;
    }
    public void setShows(ArrayList<String> shows) throws MissingShowException {
        CurrentShows.validateShows(shows);
        this.shows = shows;
    }

    public void setNewParameters(Guide dto){
        /*
        if(dto.name != null)this.name = dto.name;
        if(dto.phone != null)this.phone = dto.phone;
        if(dto.languages != null)this.languages = dto.languages;
        if(dto.shows != null)this.shows = dto.shows;
        */
    }
}
