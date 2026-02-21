package GUI;

import TalkCok.TalkCok;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private final TalkCok talkCok = new TalkCok();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("TalkCok");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTalkCok(talkCok);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
