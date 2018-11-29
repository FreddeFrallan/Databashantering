package sample;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Utils {

    public static int STANDARD_X_SIZE = 500;
    public static int STANDARD_Y_SIZE = 500;



    // ********** Buttons
    //Takes a handler as input, meaning that it makes use of the Eventhandler Interface
    public static Button createButton(String text, EventHandler handler){
        Button theButton = new Button();
        theButton.setText(text);
        theButton.setOnAction(handler);
        return theButton;
    }

    //Takes a method as input that will be called
    public static Button createButton(String text, Method method) throws Exception {
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
}
