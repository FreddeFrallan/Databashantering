package sample.GUIManager;

import javafx.scene.control.Button;
import sample.CustomScene;
import sample.MainSceneManager;
import sample.SceneNames;
import sample.Utils;

public class MainMenuScene extends CustomScene {


    public MainMenuScene(SceneNames name) throws Exception {
        Button btn1 = Utils.createButton("My Btn", getGUIMethod("guideClicked"));
        layout.getChildren().add(btn1);
        commitScene(name);
    }

    public static void guideClicked(){
        MainSceneManager.singleton.requestGotoScene(SceneNames.Guides);
    }


}
