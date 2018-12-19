package sample.GUIScenes;

import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Data.CurrentGuides;
import sample.Data.Guide;
import sample.Utils;

public class EditGuide{

    @FXML
    private TextArea editField = new TextArea();

    private BorderPane fullLayout = new BorderPane();
    private GridPane layout = new GridPane();
    private boolean isInited = false;
    private Scene s;
    private Guide targetGuide;
    private Stage window;


    //******** Constructor & Init
    private void initWindow(){
        if(isInited)
            return;
        isInited = true;
        layout.setHgap(Utils.WINDOW_X_SIZE/100);
        layout.setVgap(Utils.WINDOW_Y_SIZE/100);
        fullLayout.setCenter(layout);
        s = new Scene(fullLayout);
    }

    public EditGuide(Guide guide) {
        initWindow();
        targetGuide = guide;
        window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(guide.getName());
        window.setMinWidth(500);

        try{setupLeftSection(15, 10);
        }catch (Exception e){}
        editField.setText(guide.getInfoString());

        window.setScene(s);
        window.showAndWait();
    }


    //******** Visuals
    private void setupLeftSection(int col, int row) throws Exception {
        GridPane.setValignment(editField, VPos.TOP);
        editField.setPrefWidth(300);
        editField.setPrefHeight(200);
        editField.setDisable(false);
        editField.setStyle("-fx-opacity: 1;");
        layout.add(editField, col, row);

        HBox btns = new HBox();
        Button btn1 = Utils.createButton("Update information", event -> updateInformation());
        btns.getChildren().addAll(btn1);
        layout.add(btns, col,row+1);
    }


    //********** Events
    private void updateInformation(){
        try{
            CurrentGuides.editGuideInfo(targetGuide.getID(), editField.getText());
            window.close();
        }catch (Exception e){
            ErrorPopup.display(e.getMessage());
        }
    }

}
