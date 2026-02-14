package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;

    public DialogBox(String s, boolean isUser) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(s);

        if(isUser) {
            this.setAlignment(Pos.CENTER_RIGHT);
        } else {
            this.setAlignment(Pos.CENTER_LEFT);
        }
    }

    private void flip() {
        this.setAlignment(Pos.TOP_RIGHT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String s) {
        var db = new DialogBox(s, true);
        db.flip();
        return db;
    }

    public static DialogBox getReply(String s) {
        return new DialogBox(s, false);
    }
}
