import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.concurrent.ThreadLocalRandom;

public class App extends Application {
    int score = 0;
    int[] colorgenerator = new int[20];
    int currentStage = 1;
    int userInputCount = 0;
    boolean canClick = false;
    
    // Button references
    Button redBtn;
    Button greenBtn;
    Button yellowBtn;
    Button blueBtn;
    Label promptLabel;

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

        promptLabel = new Label();
        promptLabel.setText("SIMON");

        VBox list = new VBox();
        VBox list2 = new VBox();

        //button setup
        Image blueBtnImage = new Image("SimonColors/blue_button.png");
        ImageView blueBtnImageView = new ImageView(blueBtnImage);

        Image greenBtnImage = new Image("SimonColors/green_button.png");
        ImageView greenBtnImageView = new ImageView(greenBtnImage);

        Image redBtnImage = new Image("SimonColors/red_button.png");
        ImageView redBtnImageView = new ImageView(redBtnImage);

        Image yellowBtnImage = new Image("SimonColors/yellow_button.png");
        ImageView yellowBtnImageView = new ImageView(yellowBtnImage);

        Button startBtn = new Button();

        blueBtnImageView.setFitWidth(400);
        greenBtnImageView.setFitWidth(400);
        redBtnImageView.setFitWidth(400);
        yellowBtnImageView.setFitWidth(400);

        blueBtnImageView.setFitHeight(400);
        greenBtnImageView.setFitHeight(400);
        redBtnImageView.setFitHeight(400);
        yellowBtnImageView.setFitHeight(400);

        redBtn = new Button();
        redBtn.setGraphic(redBtnImageView);
        redBtn.setAlignment(Pos.TOP_LEFT);

        greenBtn = new Button();
        greenBtn.setGraphic(greenBtnImageView);
        greenBtn.setAlignment(Pos.TOP_RIGHT);

        yellowBtn = new Button();
        yellowBtn.setGraphic(yellowBtnImageView);
        yellowBtn.setAlignment(Pos.BOTTOM_LEFT);

        blueBtn = new Button();
        blueBtn.setGraphic(blueBtnImageView);
        blueBtn.setAlignment(Pos.BOTTOM_RIGHT);

        // Set up events
        startBtn.setText("Start Game");
        startBtn.setOnAction(event -> onStartBtn());
        
        
        redBtn.setOnAction(event -> onColorButtonClick(1));
        greenBtn.setOnAction(event -> onColorButtonClick(4));
        yellowBtn.setOnAction(event -> onColorButtonClick(3));
        blueBtn.setOnAction(event -> onColorButtonClick(2));

        // Add components to the content box.
        list.getChildren().addAll(startBtn, redBtn, greenBtn);
        list2.getChildren().addAll(blueBtn, yellowBtn);
        gameBox.getChildren().addAll(aligner);
        gameBox.getChildren().addAll(list, list2);

        // Set up window
        Scene scene = new Scene(gameBox, 1000, 800);
        stage.setScene(scene);
        stage.setTitle("Simon");
        stage.show();
    }

    // Generate random color pattern
    void generatePattern() {
        for (int i = 0; i < colorgenerator.length; i++) {
            colorgenerator[i] = ThreadLocalRandom.current().nextInt(1, 5);
        }
    }

    // Start the game
    void onStartBtn() {
        generatePattern();
        currentStage = 1;
        userInputCount = 0;
        promptLabel.setText("Round 1");
        showSequence();
    }

    // Flash a button based on what color it is
    void flashButton(int colorCode) {
        if (colorCode == 1) {
            redBtn.setStyle("-fx-opacity: 0.5;");
            new Timeline(new KeyFrame(Duration.millis(500), e -> redBtn.setStyle("-fx-opacity: 1;"))).play();
        } else if (colorCode == 2) {
            blueBtn.setStyle("-fx-opacity: 0.5;");
            new Timeline(new KeyFrame(Duration.millis(500), e -> blueBtn.setStyle("-fx-opacity: 1;"))).play();
        } else if (colorCode == 3) {
            yellowBtn.setStyle("-fx-opacity: 0.5;");
            new Timeline(new KeyFrame(Duration.millis(500), e -> yellowBtn.setStyle("-fx-opacity:  1;"))).play();
        } else if (colorCode == 4) {
            greenBtn.setStyle("-fx-opacity: 0.5;");
            new Timeline(new KeyFrame(Duration.millis(500), e -> greenBtn.setStyle("-fx-opacity: 1;"))).play();
        }
    }

    // Handle color button clicks
    void onColorButtonClick(int colorCode) {
        if (!canClick) {
            return;  // Exit if can't click
        }
        
        if (colorgenerator[userInputCount] == colorCode) {
            promptLabel.setText("Good!");
            userInputCount++;
            
            if (userInputCount == currentStage) {
                currentStage++;
                canClick = false;
                promptLabel.setText("Round " + currentStage);
                new Timeline(new KeyFrame(Duration.seconds(1.5), event -> showSequence())).play();
            }
        } else {
            promptLabel.setText("You Lose!");
            canClick = false;
        }
    }

    // Show the sequence for current stage
    void showSequence() {
        if (currentStage > 20) {
            promptLabel.setText("You Win!");
            return;
        }
        
        canClick = false;
        promptLabel.setText("Watch!");
        
        // Flash buttons with delays
        for (int i = 0; i < currentStage; i++) {
            int delay = i + 1;
            int color = colorgenerator[i];
            new Timeline(new KeyFrame(Duration.seconds(delay), event -> flashButton(color))).play();
        }
        
        // Allow clicking after sequence
        new Timeline(new KeyFrame(Duration.seconds(currentStage + 1), event -> {
            canClick = true;
            promptLabel.setText("Your turn!");
            userInputCount = 0;
        })).play();
    }
}
