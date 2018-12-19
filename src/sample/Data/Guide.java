package sample.Data;

import java.util.ArrayList;

public class Guide {

    private ArrayList<String> languages = new ArrayList<>();
    private ArrayList<String> shows = new ArrayList<>();
    private String name, phone, ID;
    private Integer age = 30;

    public Guide(){}
    public Guide(String name, String ID, ArrayList<String> languages){
        this.languages = languages;
        this.name = name;
        this.ID = ID;
    }


    @Override
    public String toString() {return name;}
    //How we display the Guides info in the Guide & edit Guide window
    public String getInfoString(){
        String s = "";
        s += "Name: " + name + "\n";
        s += "Age: " + age + "\n";
        if(phone != null)
            s += "Phone: " + phone + "\n";
        s += "Languages:\n";
        for(String l : languages)
            s += "\t" + l + "\n";
        s += "Shows:\n";
        for(String l : shows)
            s += "\t" + l + "\n";

        return s;
    }



    //*******  Getters & Setters
    public String getName(){return name;}
    public String getID(){return ID;}

    public void setName(String name){this.name = name;}
    public void setAge(int age){this.age = age;}
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
        System.out.println("Start age" + name + ", " + age.toString());
        if(dto.name != null)this.name = dto.name;
        if(dto.age != null)this.age = dto.age;
        if(dto.phone != null)this.phone = dto.phone;
        if(dto.languages != null)this.languages = dto.languages;
        if(dto.shows != null)this.shows = dto.shows;
        System.out.println("Updated params for" + name + ", " + age.toString());
    }
}
