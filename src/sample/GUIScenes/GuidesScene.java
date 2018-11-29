package sample.GUIManager;

import javafx.scene.control.Button;
import sample.CustomScene;
import sample.MainSceneManager;
import sample.SceneNames;
import sample.Utils;

public class GuidesScene extends CustomScene {

    public GuidesScene(SceneNames name) throws Exception {
        Button btn1 = Utils.createButton("Goto main", getGUIMethod("btn1Pressed"));
        layout.getChildren().add(btn1);
        commitScene(name);
    }


    public static void btn1Pressed(){
        MainSceneManager.singleton.requestGotoScene(SceneNames.MainMenu);
    }
}
