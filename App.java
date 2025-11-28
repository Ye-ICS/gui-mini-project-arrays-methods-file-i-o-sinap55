import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Template JavaFX application.
 */
public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Create components to add.
        HBox gameBox = new HBox();
        gameBox.setAlignment(Pos.CENTER);
        HBox aligner = new HBox();
        aligner.setAlignment(Pos.TOP_CENTER);

        Label promptLabel = new Label();
        promptLabel.setText("SIMON");

        VBox list = new VBox();

        VBox list2 = new VBox();

        Image blueBtnImage = new Image("SimonColors/blue_button.png");
        ImageView blueBtnImageView = new ImageView(blueBtnImage);

        Image greenBtnImage = new Image("SimonColors/green_button.png");
        ImageView greenBtnImageView = new ImageView(greenBtnImage);

        Image redBtnImage = new Image("SimonColors/red_button.png");
        ImageView redBtnImageView = new ImageView(redBtnImage);

        Image yellowBtnImage = new Image("SimonColors/yellow_button.png");
        ImageView yellowBtnImageView = new ImageView(yellowBtnImage);

        blueBtnImageView.setFitWidth(400);
        greenBtnImageView.setFitWidth(400);
        redBtnImageView.setFitWidth(400);
        yellowBtnImageView.setFitWidth(400);

        blueBtnImageView.setFitHeight(400);
        greenBtnImageView.setFitHeight(400);
        redBtnImageView.setFitHeight(400);
        yellowBtnImageView.setFitHeight(400);

        Button redBtn = new Button();
        redBtn.setGraphic(redBtnImageView);
        redBtn.setAlignment(Pos.TOP_LEFT);

        Button greenBtn = new Button();
        greenBtn.setGraphic(greenBtnImageView);
        greenBtn.setAlignment(Pos.TOP_RIGHT);

        Button yellowBtn = new Button();
        yellowBtn.setGraphic(yellowBtnImageView);
        yellowBtn.setAlignment(Pos.BOTTOM_LEFT);

        Button blueBtn = new Button();
        blueBtn.setGraphic(blueBtnImageView);
        blueBtn.setAlignment(Pos.BOTTOM_RIGHT);

        // Set up reactions (aka callbacks).

        // Add components to the content box.
        list.getChildren().addAll(redBtn, greenBtn);
        list2.getChildren().addAll(blueBtn, yellowBtn);
        gameBox.getChildren().addAll(aligner);
        gameBox.getChildren().addAll(list, list2);

        // Set up the window and display it.
        Scene scene = new Scene(gameBox, 1000, 800);
        stage.setScene(scene);
        stage.setTitle("Simon");
        stage.show();
    }
    
}
