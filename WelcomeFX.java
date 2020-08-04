package application;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class WelcomeFX extends Application {

  private List<String> args;

  private static final int WINDOW_WIDTH = 1800;
  private static final int WINDOW_HEIGHT = 1000;
  Scene SceneOne, SceneTwo, SceneThree;
  BudgetMakerDriver driver = new BudgetMakerDriver();

  @Override
  public void start(Stage primaryStage) throws Exception {
    // second scene
    
   
    
    // scene 1
    
    //top
    VBox top = new VBox();
    top.setPrefHeight(300);
    top.setPrefWidth(1800);
    Label welcome = new Label("Welcome to the Budget Maker");
    welcome.setFont(new Font("Ariel", 30));
    Label greeting = new Label("\n" + "Let's create your new budget!");
    greeting.setFont(new Font("Ariel", 22));
    Label directions = new Label("\n" + "Press the button to enter a file with your finances, when finished press done!");
    directions.setFont(new Font("Ariel", 16));
    top.getChildren().addAll(welcome, greeting, directions);
    top.setAlignment(Pos.CENTER);
    
    //mid
    HBox mid = new HBox();
    Button csv = new Button();
    FileChooser fileChooser = new FileChooser();
    csv.setText("Select CSV File");
    csv.setOnAction(action -> {
      File selectedFile = fileChooser.showOpenDialog(primaryStage);
      mid.getChildren().add(new Label("\n" + ""));
      if(selectedFile.getName().contains(".csv")) {
        
        driver.go(selectedFile);
        
      }else {
        Alert errorAlert = new Alert(AlertType.ERROR);
        errorAlert.setHeaderText("File invalid");
        errorAlert.setContentText("File must be of type .csv");
        errorAlert.showAndWait();
      }
      
    });
    mid.getChildren().add(csv);
    mid.setAlignment(Pos.TOP_CENTER);
    
    //bottom
    HBox bottom = new HBox();
    bottom.setAlignment(Pos.BOTTOM_LEFT);
    bottom.setSpacing(815);
    Button exit = new Button("Exit");
    exit.setOnAction(action -> {
      System.exit(0);
    });
    Button done = new Button("Done");
    done.setOnAction(action -> {
      primaryStage.setScene(SceneTwo);
    });
    done.setMinSize(100, 50);
    bottom.getChildren().addAll(exit, done);
    //left
    VBox left = new VBox();
    FileInputStream inputPic = new FileInputStream("Money.jpg");
    Image image = new Image(inputPic);
    ImageView newImage = new ImageView(image);
    newImage.setFitHeight(600);
    newImage.setFitWidth(400);
    left.getChildren().add(newImage);
    
    //right
    VBox right = new VBox();
    FileInputStream inputPicTwo = new FileInputStream("Money.jpg");
    Image imageTwo = new Image(inputPicTwo);
    ImageView newImageTwo = new ImageView(imageTwo);
    newImageTwo.setFitHeight(600);
    newImageTwo.setFitWidth(400);
    right.getChildren().add(newImageTwo);
    
    BorderPane sceneOneBP = new BorderPane();

    sceneOneBP.setTop(top);
    sceneOneBP.setCenter(mid);
    sceneOneBP.setBottom(bottom);
    sceneOneBP.setRight(right);
    sceneOneBP.setLeft(left);
    SceneOne = new Scene(sceneOneBP, WINDOW_WIDTH, WINDOW_HEIGHT);
    
    
    //SceneTwo
    BorderPane sceneTwoBP = new BorderPane();
    HBox topTwo = new HBox();
    Button backButton = new Button("Back");
    backButton.setOnAction(action -> {
      primaryStage.setScene(SceneOne);
    });
    topTwo.getChildren().add(backButton);
    topTwo.setPrefSize(1800, 200);
    topTwo.setSpacing(400);
    Label Header = new Label("Please answer some questions to help us better restructure your budget!");
    Header.setFont(new Font("Ariel", 30));
    topTwo.getChildren().add(Header);
    
    
    VBox leftTwo = new VBox();
    leftTwo.setPrefSize(600, 1000);
    leftTwo.setPadding(new Insets(10, 10, 10, 10));
    leftTwo.setSpacing(50);
    //question 1
    Label questionOne = new Label("How many people are in your household?" +"\n");
    questionOne.setFont(new Font("Ariel", 18));
    TextField question1 = new TextField("Answer Here");
    
    //Label space = new Label("\n" +"\n");
    //question 2
    Label questionTwo = new Label("How many children under 12 do you have?" +"\n");
    questionTwo.setFont(new Font("Ariel", 18));
    TextField question2 = new TextField("Answer Here");
    
    //Label space2 = new Label("\n" +"\n");
    //question 3
    Label questionThree = new Label("What are you saving money for?" +"\n");
    questionThree.setFont(new Font("Ariel", 18));
    TextField question3 = new TextField("Answer Here");
    
    //Label space3 = new Label("\n"+ "\n");
    //question 4
    Label questionFour = new Label("How much do you need to save?" +"\n");
    questionFour.setFont(new Font("Ariel", 18));
    TextField question4 = new TextField("Answer Here");
    
    //Label space4 = new Label("\n"+ "\n");
    //question 5
    Label questionFive = new Label("How many months do have to save? (Just enter the number) " +"\n");
    questionFive.setFont(new Font("Ariel", 18));
    TextField question5 = new TextField("Answer Here");
    
    //Label space5 = new Label("\n" + "\n");
    
    
    leftTwo.getChildren().addAll(questionOne, question1, questionTwo, question2, questionThree, question3, questionFour, question4, questionFive, question5);
    leftTwo.setAlignment(Pos.TOP_LEFT);
    
    HBox bottom2 = new HBox();
    bottom2.setAlignment(Pos.BOTTOM_LEFT);
    bottom2.setSpacing(815);
    Button exit2 = new Button("Exit");
    exit2.setOnAction(action -> {
      System.exit(0);
    });
    Button done2 = new Button("Done");
    done2.setOnAction(action -> {
      driver.setNumInHouse(question1.getText());
      driver.setKidsUnderTwelve(question2.getText());
      driver.setSavingFor(question3.getText());
      driver.setSavingFor(question4.getText());
      driver.setSavingHowLong(question5.getText());
      driver.newBudgetMaker();
      primaryStage.setScene(SceneThree);
    });
    bottom2.getChildren().addAll(exit2, done2);
    
    sceneTwoBP.setTop(topTwo);
    sceneTwoBP.setLeft(leftTwo);
    sceneTwoBP.setBottom(bottom2);
    //sceneTwoBP.setCenter(centerTwo); 
  
    SceneTwo = new Scene(sceneTwoBP, WINDOW_WIDTH, WINDOW_HEIGHT);
 
    BorderPane sceneThreeBP = new BorderPane();
    HBox top3 = new HBox();
    Label welcome3 = new Label("TODO: This is where the user will find the output of their new budget after it is processed, still working on the layout and design of this so it flows well with other code.");
    top3.getChildren().add(welcome3);
    sceneThreeBP.setTop(top3);
    
    SceneThree = new Scene(sceneThreeBP, WINDOW_WIDTH, WINDOW_HEIGHT);

    primaryStage.setTitle("Budget Maket");
    primaryStage.setScene(SceneOne);
    primaryStage.show();

  }

  public static void main(String[] args) {
    launch(args);

  }

}
