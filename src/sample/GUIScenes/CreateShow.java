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
import sample.Data.*;
import sample.Database.DBConnection;
import sample.Utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CreateShow{


    @FXML
    private ListView<Object> visualObjectsList = new ListView<>();
    private TextArea titleInputField = new TextArea();
    private TextArea areaInputField = new TextArea();
    private TextArea startInputField = new TextArea();
    private TextArea endInputField = new TextArea();
    private Stage window = new Stage();

    public CreateShow(){
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);

        //Title Input
        Text titleText = new Text("New Title");
        titleText.setFont(new Font(30));
        titleInputField.setPrefWidth(50);
        titleInputField.setPrefHeight(10);

        //Area Input
        Text areaText = new Text("Area");
        areaText.setFont(new Font(30));
        areaInputField.setPrefWidth(50);
        areaInputField.setPrefHeight(10);

        //Start Input
        Text startText = new Text("Start Time");
        startText.setFont(new Font(30));
        endInputField.setPrefWidth(50);
        endInputField.setPrefHeight(10);

        //End Input
        Text endText = new Text("Start Time");
        endText.setFont(new Font(30));
        startInputField.setPrefWidth(50);
        startInputField.setPrefHeight(10);

        //Objects Text
        Text objectsText = new Text("Included Objects");
        titleText.setFont(new Font(30));

        //Visual Objects List
        visualObjectsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        visualObjectsList.setPrefHeight(200);
        visualObjectsList.getItems().setAll(CurrentArtObjects.getAllObjects());

        //Create Button
        Button createBtn = Utils.createButton("Create New Show", event -> createShowPressed());

        layout.getChildren().addAll(
                titleText, titleInputField,
                areaText, areaInputField,
                startText, startInputField,
                endText, endInputField,
                objectsText, visualObjectsList, createBtn);

        window.setScene(new Scene(layout));
        window.showAndWait();
    }


    //********** Handlers
    private void createShowPressed(){
        ObservableList<Object> temp = visualObjectsList.getSelectionModel().getSelectedItems();

        try{
            String title = titleInputField.getText();
            BigDecimal area =  BigDecimal.valueOf(Double.parseDouble(areaInputField.getText()));

            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Timestamp start = new java.sql.Timestamp(date.parse(startInputField.getText()).getTime());
            Timestamp end = new java.sql.Timestamp(date.parse(startInputField.getText()).getTime());

            //Sanity checks
            if(area.intValue() < 0 || area.intValue() > 5100)
                throw new DataException("Invalid Area");
            if(start.getTime() < end.getTime())
                throw new DataException("End time can't be earlier than start time");

            try{
                Show existingShow = CurrentShows.getShowFromTitle(title);
                if(existingShow.getStartTime() == start)
                    throw new DataException("Show with the same title and start time already exists");
            }
            catch (DataException e){throw e;}
            catch (Exception e){}

            ArrayList<ArtObject> objects = new ArrayList<>();
            for(Object o : temp) objects.add((ArtObject)o);

            Show newShow = new Show(-1, title, area, start, end, objects);
            long newKey = CurrentShows.insertNewShow(newShow);
            if(newKey == -1)
                throw new DatabaseException("Unable to insert show into database");

            newShow.setID(newKey);
            CurrentArtObjects.insertNewObjectsToShow(newShow, objects);

            DBConnection.closeConnection();
            window.close();
        }catch (Exception e){ ErrorPopup.display(e.getMessage()); }
    }

}
