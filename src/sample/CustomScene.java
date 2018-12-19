package sample;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import java.lang.reflect.Method;

public abstract class CustomScene {

    private BorderPane fullLayout = new BorderPane();
    protected GridPane layout = new GridPane();
    private Scene thisScene;

    public abstract void refreshScene();

    protected void initGUI(){
        initTopMenu();
        initCenterGrid();
    }

    private void initCenterGrid(){
        layout.setHgap(Utils.WINDOW_X_SIZE/100);
        layout.setVgap(Utils.WINDOW_Y_SIZE/100);
        fullLayout.setCenter(layout);
    }

    private void initTopMenu(){
        HBox topMenu = new HBox();
        Button b1 = Utils.createButton("Main", event -> {MainSceneManager.singleton.requestGotoScene(SceneNames.MainMenu);});
        Button b2 = Utils.createButton("Guides", event -> {MainSceneManager.singleton.requestGotoScene(SceneNames.Guides);});
        Button b3 = Utils.createButton("Shows", event -> {MainSceneManager.singleton.requestGotoScene(SceneNames.Show);});

        topMenu.getChildren().addAll(b1, b2, b3);
        fullLayout.setTop(topMenu);
    }

    protected Method getGUIMethod(String name){
        try {return this.getClass().getMethod(name, null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void commitScene(SceneNames name){
        thisScene = new Scene(fullLayout, Utils.WINDOW_X_SIZE, Utils.WINDOW_Y_SIZE);
        MainSceneManager.singleton.addScene(name, this);
    }
    public Scene getScene(){return thisScene;}
}
