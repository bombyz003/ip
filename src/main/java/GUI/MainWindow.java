package GUI;

import TalkCok.TalkCok;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox chatbox;
    @FXML
    private TextField userTyped;
    @FXML
    private Button sendButton;

    private TalkCok talkCok;

    public void initialise() {
        scrollPane.vvalueProperty().bind(chatbox.heightProperty());
    }

    public void setTalkCok(TalkCok tc) {
        talkCok = tc;
    }

    @FXML
    private void handleInput() {
        String userInput = userTyped.getText();
        String reply = talkCok.getResponse(userInput);
        if (userInput.equalsIgnoreCase("bye")) {
            closeWindow();
        }
        chatbox.getChildren().addAll(
                DialogBox.getUserDialog(userInput), DialogBox.getReply(reply)
        );
        userTyped.clear();
    }

    public void closeWindow() {
        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
        delay.setOnFinished(e -> {
            Stage stage = (Stage) userTyped.getScene().getWindow();
            stage.close();
        });
        delay.play();
    }
}
