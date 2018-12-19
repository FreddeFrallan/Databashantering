package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Data.CurrentGuides;
import sample.Data.CurrentShows;
import sample.Data.Guide;
import sample.GUIManager.GuidesScene;
import sample.GUIManager.MainMenuScene;
import sample.GUIScenes.ShowsScene;

import java.util.ArrayList;
import java.util.Hashtable;


public class MainSceneManager extends Application{

    public static MainSceneManager singleton;

    private Hashtable<SceneNames, CustomScene> scenes = new Hashtable<SceneNames, CustomScene>();
    private CustomScene currentScene;
    private Stage theStage;

    //****************** Init
    public static void main(String[] args) {launch(args);}
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Konstmuseum");
        theStage = primaryStage;
        theStage.setOnCloseRequest(e -> onExitApplication());
        singleton = this;
        initTestData();

        initScenes();
        requestGotoScene(SceneNames.MainMenu);
        primaryStage.show();
    }

    private void initScenes() throws Exception {
        new MainMenuScene(SceneNames.MainMenu);
        new GuidesScene(SceneNames.Guides);
        new ShowsScene(SceneNames.Show);
    }


    private void initTestData(){
        CurrentGuides.insertGuide(new Guide("Fredrik Carlsson", "1", new ArrayList<>()));
        CurrentGuides.insertGuide(new Guide("Erik Lenas", "2", new ArrayList<>()));
        CurrentGuides.insertGuide(new Guide("Melker Mossberg", "3",new ArrayList<>()));
    }
    //**********************************

    //****************** Scene management
    public void addScene(SceneNames name, CustomScene theScene){scenes.put(name, theScene);}
    public void requestGotoScene(SceneNames targetScene){ gotoScene(scenes.get(targetScene)); }
    private void gotoScene(CustomScene newScene){
        if(newScene == currentScene)
            return;
        currentScene = newScene;
        newScene.refreshScene();
        theStage.setScene(newScene.getScene());
    }
    //***********************************

    private void onExitApplication(){
        System.out.println("Goodbye");
    }
}
