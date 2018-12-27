package sample.GUIScenes;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sample.CustomScene;
import sample.Data.CurrentShows;
import sample.Data.Show;
import sample.SceneNames;
import sample.Utils;

public class ShowsScene extends CustomScene {

    @FXML
    private ListView<Show> visualShowList = new ListView<>();
    private TextArea infoArea = new TextArea();

    //************* Init
    public ShowsScene(SceneNames name) throws Exception {
        initGUI();
        setupLeftSection(2, 18);
        setupRightSection(10, 18);
        commitScene(name);
    }

    private void setupLeftSection(int col, int row) {
        GridPane.setValignment(infoArea, VPos.TOP);
        infoArea.setPrefWidth(300);
        infoArea.setPrefHeight(200);
        infoArea.setEditable(false);
        infoArea.setStyle("-fx-opacity: 1;");
        layout.add(infoArea, col, row);
    }

    private void setupRightSection(int col, int row){
        //Text
        Text guidesText = new Text("Current Shows");
        guidesText.setFont(new Font(30));
        layout.add(guidesText, col, row-1);
        GridPane.setHalignment(guidesText, HPos.CENTER);

        //List
        layout.add(visualShowList, col, row);
        visualShowList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Show>() {
            @Override
            public void changed(ObservableValue<? extends Show> observable, Show oldValue, Show newValue) {
                showSelected(newValue);
            }
        });

        //Buttons
        HBox btns = new HBox();
        Button addBtn = Utils.createButton("New Show", event -> newShowPressed());
        Button delBtn = Utils.createButton("Delete", event -> deletePressed());
        btns.getChildren().addAll(addBtn, delBtn);
        layout.add(btns, col,row+1);
    }
    //********************


    @Override
    public void refreshScene() {
        visualShowList.getItems().setAll(CurrentShows.getAllShows());
        infoArea.setText("");
    }


    //********** Handlers
    private void deletePressed(){
        ObservableList<Integer> temp = visualShowList.getSelectionModel().getSelectedIndices();
        if(temp.isEmpty() == false){
            CurrentShows.removeShow(
                    visualShowList.getItems().get(temp.get(0)).getID()
            );
            refreshScene();
        }
    }

    private void showSelected(Show newTarget){
        try{infoArea.setText(newTarget.getInfoString());
        }catch (Exception e){}
    }

    private void newShowPressed(){
        new CreateShow();
        refreshScene();
    }
}
