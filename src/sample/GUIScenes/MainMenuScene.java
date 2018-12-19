package sample.GUIManager;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import sample.CustomScene;
import sample.MainSceneManager;
import sample.SceneNames;
import sample.Utils;

public class MainMenuScene extends CustomScene {

    public MainMenuScene(SceneNames name) throws Exception {
        initGUI();
        createCenterGUI();
        commitScene(name);
    }

    @Override
    public void refreshScene() {

    }


    private void createCenterGUI(){
        System.out.println(Utils.WINDOW_Y_SIZE/10);
        Text welcomeText = new Text("Main Menu");
        welcomeText.setFont(new Font(30));
        layout.add(welcomeText, 35, 10);
    }

}
