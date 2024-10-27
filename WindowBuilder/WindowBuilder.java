import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class WindowBuilder extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("My Awesome Window");

        Pane pane = new Pane();
        pane.setPrefSize(666, 325);
        pane.setStyle("-fx-background-color: #1e1e1e;");

        Button element1 = new Button("Connection");
        element1.setLayoutX(285.68);
        element1.setLayoutY(215.00);
        element1.setPrefWidth(93.00);
        element1.setPrefHeight(39.00);
        element1.setDisable(false);
        element1.setFont(Font.loadFont(getClass().getResourceAsStream("/resources/fonts/Lato.ttf"), 14.00));
        element1.setStyle("-fx-background-color: #2e2e2e; -fx-text-fill: #D9D9D9; -fx-border-color: #979797; -fx-border-radius: 4px; -fx-background-radius: 4px; -fx-border-width: 1px;");
        element1.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { element1.setBackground(new Background(new BackgroundFill(Color.web("#232323"), new CornerRadii(4.00), null))); });
        element1.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> { element1.setBackground(new Background(new BackgroundFill(Color.web("#2e2e2e"), new CornerRadii(4.00), null))); });
        pane.getChildren().add(element1);

        PasswordField element2 = new PasswordField();
        element2.setText("");
        element2.setLayoutX(221.00);
        element2.setLayoutY(166.00);
        element2.setPrefWidth(225.00);
        element2.setPrefHeight(21.00);
        element2.setPromptText("Your Password!");
        element2.setFont(Font.loadFont(getClass().getResourceAsStream("/resources/fonts/Lato.ttf"), 14.00));
        element2.setStyle("-fx-background-color: #B2B2B2; -fx-text-fill: #353535; -fx-border-color: #979797; -fx-border-width: 0px; -fx-border-radius: 2px; -fx-prompt-text-fill: #656565;");
        pane.getChildren().add(element2);

        TextField element3 = new TextField("");
        element3.setLayoutX(224.00);
        element3.setLayoutY(115.00);
        element3.setPrefWidth(225.00);
        element3.setPrefHeight(21.00);
        element3.setPromptText("firstName.lastName@example.com");
        element3.setFont(Font.loadFont(getClass().getResourceAsStream("/resources/fonts/Lato.ttf"), 14.00));
        element3.setStyle("-fx-background-color: #B2B2B2; -fx-text-fill: #353535; -fx-border-color: #979797; -fx-border-width: 0px; -fx-border-radius: 2px; -fx-prompt-text-fill: #656565;");
        pane.getChildren().add(element3);

        Label element5 = new Label("LOG IN !");
        element5.setLayoutX(276.0000305175781);
        element5.setLayoutY(35.25);
        element5.setPrefWidth(119);
        element5.setPrefHeight(35);
        element5.setFont(Font.loadFont(getClass().getResourceAsStream("/resources/fonts/Lato.ttf"), 30.00));
        element5.setStyle("-fx-text-fill: #D9D9D9;");
        pane.getChildren().add(element5);

        Scene scene = new Scene(pane, 666, 325);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}