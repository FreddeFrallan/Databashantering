package sample.GUIScenes;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Data.ArtObject;
import sample.Data.CurrentArtObjects;
import sample.Data.CurrentShows;
import sample.Data.Show;
import sample.Utils;

import java.util.ArrayList;

public class CreateShow{


    @FXML
    private ListView<Object> visualObjectsList = new ListView<>();
    private TextArea titleArea = new TextArea();
    private Stage window = new Stage();

    public CreateShow(){
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);

        //Title Text
        Text titleText = new Text("New Title");
        titleText.setFont(new Font(30));

        //Title inputArea
        titleArea.setPrefWidth(50);
        titleArea.setPrefHeight(10);

        //Objects Text
        Text objectsText = new Text("Included Objects");
        titleText.setFont(new Font(30));

        //Visual Objects List
        visualObjectsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        visualObjectsList.setPrefHeight(200);
        visualObjectsList.getItems().setAll(CurrentArtObjects.getAllObjects());

        //Create Button
        Button createBtn = Utils.createButton("Create New Show", event -> createShowPressed());

        layout.getChildren().addAll(titleText, titleArea, objectsText, visualObjectsList, createBtn);

        window.setScene(new Scene(layout));
        window.showAndWait();
    }


    //********** Handlers
    private void createShowPressed(){
        ObservableList<Object> temp = visualObjectsList.getSelectionModel().getSelectedItems();

        System.out.println("Creating new show with title: " + titleArea.getText());
        ArrayList<ArtObject> objects = new ArrayList<>();
        for(Object o : temp){
            System.out.println("With item: " +  o.toString());
            objects.add((ArtObject)o);
        }

        //CurrentShows.addNewShow(new Show(titleArea.getText(), objects));
        window.close();
    }

}
