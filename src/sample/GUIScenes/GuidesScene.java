package sample.GUIManager;

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
import sample.Data.CurrentGuides;
import sample.Data.Guide;
import sample.GUIScenes.EditGuide;
import sample.SceneNames;
import sample.Utils;

public class GuidesScene extends CustomScene {

    @FXML
    private ListView<Guide> visualGuideList = new ListView<>();
    private TextArea selectedUser = new TextArea();

    //************* Init
    public GuidesScene(SceneNames name) throws Exception {
        initGUI();
        setupLeftSection(2, 18);
        setupRightSection(10, 18);
        commitScene(name);
    }

    private void setupLeftSection(int col, int row) throws Exception {
        GridPane.setValignment(selectedUser, VPos.TOP);
        selectedUser.setPrefWidth(300);
        selectedUser.setPrefHeight(200);
        selectedUser.setEditable(false);
        selectedUser.setStyle("-fx-opacity: 1;");
        layout.add(selectedUser, col, row);
    }

    private void setupRightSection(int col, int row){
        //Text
        Text guidesText = new Text("Working Guides");
        guidesText.setFont(new Font(30));
        layout.add(guidesText, col, row-1);
        GridPane.setHalignment(guidesText, HPos.CENTER);

        //List
        layout.add(visualGuideList, col, row);
        visualGuideList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Guide>() {
            @Override
            public void changed(ObservableValue<? extends Guide> observable, Guide oldValue, Guide newValue) {
                guideSelected(newValue);
            }
        });

        //Buttons
        HBox btns = new HBox();
        Button editBtn = Utils.createButton("Edit Guide", event -> editGuidePressed());
        Button delBtm = Utils.createButton("Delete", event -> deletePressed());
        btns.getChildren().addAll(editBtn, delBtm);
        layout.add(btns, col,row+1);
    }
    //********************


    @Override
    public void refreshScene() {
        visualGuideList.getItems().setAll(CurrentGuides.getAllGuides());
        selectedUser.setText("");
    }


    //********** Handlers
    private void deletePressed(){
        ObservableList<Integer> temp = visualGuideList.getSelectionModel().getSelectedIndices();
        if(temp.isEmpty() == false){
            CurrentGuides.removeGuide(
                visualGuideList.getItems().get(temp.get(0)).getID()
            );
            refreshScene();
        }
    }

    private void editGuidePressed(){
        ObservableList<Integer> temp = visualGuideList.getSelectionModel().getSelectedIndices();
        if(temp.isEmpty() == false){
            new EditGuide(visualGuideList.getItems().get(temp.get(0)));
            refreshScene();
        }
    }

    private void guideSelected(Guide newTarget){
        try{selectedUser.setText(newTarget.getInfoString());
        }catch (Exception e){}
    }
}
