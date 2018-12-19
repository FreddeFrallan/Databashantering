package sample.GUIScenes;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static sample.Utils.createButton;

public class ErrorPopup {


    public static void display(String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Error");
        window.setMinWidth(250);

        VBox layout = new VBox();
        Label l = new Label();
        l.setText(message);
        Button b = createButton("Ok", e-> {window.close();});
        layout.getChildren().addAll(l, b);
        layout.setAlignment(Pos.CENTER);

        Scene s = new Scene(layout);
        window.setScene(s);
        window.showAndWait();
    }

}
