import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
        VBox gameBox = new VBox();
        gameBox.setAlignment(Pos.CENTER);
        HBox aligner = new HBox();
        aligner.setAlignment(Pos.TOP_CENTER);

        Label promptLabel = new Label();
        promptLabel.setText("SIMON");

        Circle circle1 = new Circle(40); // radius

        Button RedBtn = new Button();
        RedBtn.setShape(circle1);
        RedBtn.setMinSize(80, 80); // ensure size fits the circle
        RedBtn.setMaxSize(80, 80);
        RedBtn.setAlignment(Pos.TOP_LEFT);
        RedBtn.setStyle("-fx-background-color: red; -fx-text-fill: red;");


        Button greenBtn = new Button();
        greenBtn.setShape(circle1);
       greenBtn.setMinSize(80, 80); // ensure size fits the circle
        greenBtn.setMaxSize(80, 80);
        greenBtn.setAlignment(Pos.TOP_LEFT);
        greenBtn.setStyle("-fx-background-color: green; -fx-text-fill: green;");

        Button yellowBtn = new Button();
        
        Circle circle2 = new Circle(40); // radius

        yellowBtn.setShape(circle2);
        yellowBtn.setMinSize(80, 80); // ensure size fits the circle
        yellowBtn.setMaxSize(80, 80);
        yellowBtn.setAlignment(Pos.TOP_RIGHT);
        yellowBtn.setStyle("-fx-background-color: yellow; -fx-text-fill: yellow;");
        // Set up reactions (aka callbacks).


        // Add components to the content box.
        gameBox.getChildren().addAll(aligner);
        aligner.getChildren().addAll(RedBtn,yellowBtn,greenBtn);


        // Set up the window and display it.
        Scene scene = new Scene(gameBox, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Simon");
        stage.show();
    }


}
