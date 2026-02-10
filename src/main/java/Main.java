import TalkCok.TalkCok;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private ScrollPane scrollPane;
    private VBox chatbox;
    private TextField input;

    private TalkCok talky = new TalkCok();

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        chatbox = new VBox();
        scrollPane.setContent(chatbox);

        input = new TextField();
        Button sendButton = new Button("Send");
        sendButton.setOnMouseClicked((event) -> handleInput());
        input.setOnAction((event) -> handleInput());

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, input, sendButton);

        stage.setTitle("TalkCok");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        chatbox.setPrefHeight(Region.USE_COMPUTED_SIZE);

        input.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 5.0);
        AnchorPane.setRightAnchor(sendButton, 5.0);
        AnchorPane.setLeftAnchor(input, 5.0);
        AnchorPane.setBottomAnchor(input, 5.0);

        chatbox.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));

        stage.setScene(new Scene(mainLayout));
        stage.show();
    }

    private void handleInput() {
        String userInput = input.getText();
        String talkyReply = talky.getResponse(userInput);
        chatbox.getChildren().addAll(
                DialogBox.getUserDialog(userInput), DialogBox.getReply(talkyReply)
        );
        input.clear();
    }
}
