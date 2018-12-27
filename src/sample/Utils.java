package sample;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class Utils {

    public static int WINDOW_X_SIZE = 1280/2;
    public static int WINDOW_Y_SIZE = 720/2;


    // ********** Buttons
    //Takes a handler as input, meaning that it makes use of the Eventhandler Interface
    public static Button createButton(String text, EventHandler handler){
        Button theButton = new Button();
        theButton.setText(text);
        theButton.setOnAction(handler);
        return theButton;
    }

    //Takes a method as input that will be called
    public static Button createButton(String text, Method method) {
        Button theButton = new Button();
        theButton.setText(text);

        theButton.setOnAction(e -> {
            try {method.invoke(null, null);}
            catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        return theButton;
    }
    //************************************

    public static boolean containsDuplicates(ArrayList l){
        for(int i = 0; i < l.size(); i++)
            for(int j = i+1; j < l.size(); j++){
                if(l.get(i).equals(l.get(j)))
                    return true;
            }

        return false;
    }
}
