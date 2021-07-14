package by.skalem.griefalerts.griefalerts;


import java.util.ArrayList;

public class Friends {

    ArrayList<String> friends = new ArrayList<String>();

    public Friends(ArrayList<String> friends){
        this.friends = friends;
    }

    public ArrayList<String> List(){
        return friends;
    }

}
