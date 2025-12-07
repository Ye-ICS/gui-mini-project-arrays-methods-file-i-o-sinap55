import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

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
    TextField highScoreBox;

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
        VBox contextBox = new VBox();
        contextBox.setAlignment(Pos.CENTER);

        promptLabel = new Label();
        promptLabel.setText("SIMON");

        highScoreBox = new TextField();
        highScoreBox.setEditable(false); // make it read only   
        highScoreBox.setText(String.valueOf(readHighScore()));

        VBox redGreenContainer = new VBox();
        VBox blueYellowContainer = new VBox();

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
        // set sizing for buttons
        blueBtnImageView.setFitWidth(200);
        greenBtnImageView.setFitWidth(200);
        redBtnImageView.setFitWidth(200);
        yellowBtnImageView.setFitWidth(200);

        blueBtnImageView.setFitHeight(200);
        greenBtnImageView.setFitHeight(200);
        redBtnImageView.setFitHeight(200);
        yellowBtnImageView.setFitHeight(200);

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
        blueBtn.setOnAction(event -> onColorButtonClick(2));
        yellowBtn.setOnAction(event -> onColorButtonClick(3));
        greenBtn.setOnAction(event -> onColorButtonClick(4));
        
        

        // Add components to the content box.
        redGreenContainer.getChildren().addAll(redBtn, greenBtn);
        blueYellowContainer.getChildren().addAll(blueBtn, yellowBtn);
        gameBox.getChildren().addAll(aligner,redGreenContainer, blueYellowContainer);
        contextBox.getChildren().addAll(gameBox, promptLabel, highScoreBox, startBtn);

        // Set up window
        Scene scene = new Scene(contextBox, 400, 450);
        stage.setScene(scene);
        stage.setTitle("Simon");
        stage.show();
    }


    // reader for the high score
    int readHighScore() {
        try (Scanner sc = new Scanner(new File("highscore.txt"))) {
            if (sc.hasNextLine()) {
                String currentHigh = sc.nextLine();
                return Integer.parseInt(currentHigh);
            }
        } catch (FileNotFoundException event) { 
            return 0; // if  nothing found return 0
        }
        return 0;
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
            new Timeline(new KeyFrame(Duration.millis(500), event -> redBtn.setStyle("-fx-opacity: 1;"))).play();
        } else if (colorCode == 2) {
            blueBtn.setStyle("-fx-opacity: 0.5;");
            new Timeline(new KeyFrame(Duration.millis(500), event -> blueBtn.setStyle("-fx-opacity: 1;"))).play();
        } else if (colorCode == 3) {
            yellowBtn.setStyle("-fx-opacity: 0.5;");
            new Timeline(new KeyFrame(Duration.millis(500), event -> yellowBtn.setStyle("-fx-opacity: 1;"))).play();
        } else if (colorCode == 4) {
            greenBtn.setStyle("-fx-opacity: 0.5;");
            new Timeline(new KeyFrame(Duration.millis(500), event -> greenBtn.setStyle("-fx-opacity: 1;"))).play();
        }
    }

    // Handle color button clicks
    void onColorButtonClick(int colorCode){
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
                // save high score after completing a round
                highScorePrinterAndReader();
                new Timeline(new KeyFrame(Duration.seconds(1.5), event -> showSequence())).play();
            }
        } else {
            promptLabel.setText("You Lose!");
            canClick = false;
            // save high score when losing
            highScorePrinterAndReader();
        }
    }

    // Show the sequence for current stage + win
    void showSequence() {
        if (currentStage > colorgenerator.length) {
            promptLabel.setText("You Win!");
            // save high score on full win
            highScorePrinterAndReader();
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

    void highScorePrinterAndReader() {
        int existingHigh = 0;

        try (Scanner sc = new Scanner(new File("highscore.txt"))) {
            if (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                try {
                    existingHigh = Integer.parseInt(line);
                } catch (NumberFormatException ex) {
                    existingHigh = 0;
                }
            }
        } catch (FileNotFoundException ex) {
            // file not found -> existingHigh remains 0
        }

        int scoreToWrite = currentStage - 1;

        if (scoreToWrite > existingHigh) {
            try (PrintWriter pw = new PrintWriter(new File("highscore.txt"))) {
                pw.print(scoreToWrite);
                // update the UI box
                highScoreBox.setText(String.valueOf(scoreToWrite));
            } catch (FileNotFoundException ex) {
                // cannot write file
            }
        } else {
            // ensure UI shows current stored high score
            highScoreBox.setText(String.valueOf(existingHigh));
        }
    }
}
