package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.GUIManager.GuidesScene;
import sample.GUIManager.MainMenuScene;
import java.util.Hashtable;


public class MainSceneManager extends Application{

    public static MainSceneManager singleton;

    private Hashtable<SceneNames, Scene> scenes = new Hashtable<SceneNames, Scene>();
    private Scene currentScene;
    private Stage theStage;

    //****************** Init
    public static void main(String[] args) {launch(args);}
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Konstmuseum");
        theStage = primaryStage;
        theStage.setOnCloseRequest(e -> onExitApplication());
        singleton = this;


        initScenes();
        requestGotoScene(SceneNames.MainMenu);
        primaryStage.show();
    }

    private void initScenes() throws Exception {
        new MainMenuScene(SceneNames.MainMenu);
        new GuidesScene(SceneNames.Guides);
    }
    //**********************************

    //****************** Scene management
    public void addScene(SceneNames name, Scene theScene){scenes.put(name, theScene);}
    public void requestGotoScene(SceneNames targetScene){ gotoScene(scenes.get(targetScene)); }
    private void gotoScene(Scene newScene){
        if(newScene == currentScene)
            return;
        currentScene = newScene;
        theStage.setScene(newScene);
    }
    //***********************************

    private void onExitApplication(){
        System.out.println("Goodbye");
    }
}
