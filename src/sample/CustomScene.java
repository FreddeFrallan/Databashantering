package sample;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import java.lang.reflect.Method;

public abstract class CustomScene {

    protected StackPane layout = new StackPane();



    protected Method getGUIMethod(String name){
        try {return this.getClass().getMethod(name, null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void commitScene(SceneNames name){
        MainSceneManager.singleton.addScene(name, getScene());
    }
    private Scene getScene(){
        return new Scene(layout, Utils.STANDARD_X_SIZE, Utils.STANDARD_Y_SIZE);
    }
}
