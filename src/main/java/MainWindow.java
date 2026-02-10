import TalkCok.TalkCok;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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
        chatbox.getChildren().addAll(
                DialogBox.getUserDialog(userInput), DialogBox.getReply(reply)
        );
        userTyped.clear();
    }
}
