package application;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner; 
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/**
 * GUI that creates and runs a budgeting application
 * 
 * @author jerem
 *
 */
public class WelcomeFX extends Application {

  private List<String> args;

  private static final int WINDOW_WIDTH = 1800;
  private static final int WINDOW_HEIGHT = 1000;
  private Scene FileScene;
  File selectedFile;
  
  static CreateArrays ca;
  
  
  
  private class BudgetNode<String, Integer> {
    private String key;
    private Integer value; 


    private BudgetNode(String key, Integer value) {
      this.key = key;
      this.value = value;

    }
    /**
     * Getter to get the key of a node
     * @return key
     */
    public String getKey() {
      return key;
    }
    /**
     * Getter to get the value of a node
     * @return value
     */
    public Integer getValue() {
      return value;
    }
  }
  
  
  /**
   * starts the GUI by calling the first scene method
   * @param primaryStage
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    args = this.getParameters().getRaw();
    
    ca = new CreateArrays();
    
    Scene FileScene = createFileScene(primaryStage);
    primaryStage.setTitle("Budget Maker");
    primaryStage.setScene(FileScene);
    primaryStage.show();
    

  }
  /**
   * creates the initial scene of the GUI that contains a welcome message and launches the apllication
   * @param primaryStage
   * @return SceneOne
   * @throws FileNotFoundException
   */
  public Scene createFileScene(Stage primaryStage) throws FileNotFoundException {
// scene 1
    BorderPane sceneOneBP = new BorderPane();
    Scene SceneOne = new Scene(sceneOneBP, WINDOW_WIDTH, WINDOW_HEIGHT);
    
    //top with greeting 
    VBox top = new VBox();
    top.getChildren().add(toolBar(primaryStage));
    top.setPrefHeight(300);
    top.setPrefWidth(1800);
    Label welcome = new Label("Welcome to the Budget Maker");
    welcome.setFont(new Font("Ariel", 30));
    Label greeting = new Label("\n" + "Let's create your new budget!");
    greeting.setFont(new Font("Ariel", 22));
    Label directions = new Label("\n" + "Press the button below to enter the Budget Maker!");
    directions.setFont(new Font("Ariel", 16));
    top.getChildren().addAll(welcome, greeting, directions);
    top.setAlignment(Pos.CENTER);
    
    //mid that contains a launch button for the next scene
    HBox mid = new HBox();
    mid.setPadding(new Insets(30, 30, 30, 30));
    mid.setAlignment(Pos.CENTER);
    Button launch = new Button("Launch Budget Maker"); 
    launch.setShape(new Circle(200));
    launch.setMaxSize(200, 200);
    launch.setMinSize(200, 200);
  
    mid.getChildren().add(launch);
    mid.setAlignment(Pos.TOP_CENTER);
    
    
    
    //left that adds a picture to the first scene
    VBox left = new VBox();
    FileInputStream inputPic = new FileInputStream("Money.jpg");
    Image image = new Image(inputPic);
    ImageView newImage = new ImageView(image);
    newImage.setFitHeight(600);
    newImage.setFitWidth(400);
    left.getChildren().add(newImage);
    
    //right that adds a picture to the first scene
    VBox right = new VBox();
    FileInputStream inputPicTwo = new FileInputStream("Money.jpg");
    Image imageTwo = new Image(inputPicTwo);
    ImageView newImageTwo = new ImageView(imageTwo);
    newImageTwo.setFitHeight(600);
    newImageTwo.setFitWidth(400);
    right.getChildren().add(newImageTwo);
      
    //launches the GUI to the main Scene
    launch.setOnAction(e-> primaryStage.setScene(createMainScene(primaryStage)));
    

    sceneOneBP.setTop(top);
    sceneOneBP.setCenter(mid);
    
    sceneOneBP.setRight(right);
    sceneOneBP.setLeft(left);
    return SceneOne;
  }
  

  private Scene createMainScene(Stage primaryStage) {
    //Scene two
    //Sets the title and adds a button for the user to load data into the calculation method
    BorderPane sceneTwoBP = new BorderPane();
    Scene SceneTwo = new Scene(sceneTwoBP, WINDOW_WIDTH, WINDOW_HEIGHT);
    VBox top3 = new VBox();
    top3.getChildren().add(toolBar(primaryStage));
    Label welcome3 = new Label("Click buttons below to view pie charts of your old and new Budget after loading data!");
    Label directions3 = new Label("\n" + "Click the button below to input your data");
    directions3.setFont(new Font("Ariel", 24));
    Label space3 = new Label("\n");
    welcome3.setFont(new Font("Ariel", 30));
    Button addData = new Button("Budget Data");
    addData.setShape(new Circle(100));
    addData.setMaxSize(100, 100);
    addData.setMinSize(100, 100);
    top3.getChildren().addAll(welcome3, directions3, space3, addData);
    top3.setAlignment(Pos.CENTER);
    sceneTwoBP.setTop(top3);
    
    //Vbox that contains the buttons controlling the old budget pie charts
    VBox left3 = new VBox();
    left3.setPadding(new Insets(150,150,150,300));
    left3.setSpacing(60);
    left3.setAlignment(Pos.CENTER_LEFT);
    
    //Buttons for old budget pie charts
  
    Button oldMonthly = new Button("Old Monthly Costs");
    oldMonthly.setShape(new Circle(100));
    oldMonthly.setMinSize(100, 100);
    oldMonthly.setMaxSize(100, 100);
    left3.getChildren().add(oldMonthly);
    
    Button oldWeekly = new Button("Old Weekly Costs");
    oldWeekly.setShape(new Circle(100));
    oldWeekly.setMinSize(100, 100);
    oldWeekly.setMaxSize(100, 100);
    left3.getChildren().add(oldWeekly);
    
    Button oldDaily = new Button("Old Daily Costs");
    oldDaily.setShape(new Circle(100));
    oldDaily.setMinSize(100, 100);
    oldDaily.setMaxSize(100, 100); 
    
    left3.getChildren().add(oldDaily);
    
    //VBox that contains the button for users to view new budget pie charts
    VBox right3 = new VBox();
    right3.setPadding(new Insets(150,300,150,150));
    right3.setSpacing(60);
    //Buttons for new budget pie charts
    Button NewMonthly = new Button("New Monthly");
    NewMonthly.setShape(new Circle(100));
    NewMonthly.setMinSize(100, 100);
    NewMonthly.setMaxSize(100, 100);
    right3.getChildren().add(NewMonthly);
    
    Button NewWeekly = new Button("New Weekly");
    NewWeekly.setShape(new Circle(100));
    NewWeekly.setMinSize(100, 100);
    NewWeekly.setMaxSize(100, 100);
    right3.getChildren().add(NewWeekly);
    
    Button NewDaily = new Button("New Daily");
    NewDaily.setShape(new Circle(100));
    NewDaily.setMinSize(100, 100);
    NewDaily.setMaxSize(100, 100); 
    
    right3.getChildren().add(NewDaily);
    //Vbox that contains the buttons for a user to view spending pie charts
    VBox mid3 = new VBox();
    mid3.setPadding(new Insets(150,150,150,150));
    mid3.setSpacing(60);
    mid3.setAlignment(Pos.CENTER);
    //buttons for spending pie charts
    Button oldTotalSpending = new Button("Old Spending");
    oldTotalSpending.setShape(new Circle(100));
    oldTotalSpending.setMinSize(100, 100);
    oldTotalSpending.setMaxSize(100, 100);
    
    Button newTotalSpending = new Button("New Spending");
    newTotalSpending.setShape(new Circle(100));
    newTotalSpending.setMinSize(100, 100);
    newTotalSpending.setMaxSize(100, 100);
    
    mid3.getChildren().addAll(oldTotalSpending, newTotalSpending);
    //Sets what all the buttons do when pressed, such as what scene to go to after this
    addData.setOnAction(e -> primaryStage.setScene(ChooseFileScene(primaryStage)));
    oldMonthly.setOnAction(e-> primaryStage.setScene(oldMonthlyPC(primaryStage)));
    oldWeekly.setOnAction(e -> primaryStage.setScene(oldWeeklyPC(primaryStage)));
    oldDaily.setOnAction(e-> primaryStage.setScene(oldDailyPC(primaryStage)));
    NewMonthly.setOnAction(e-> primaryStage.setScene(newMonthlyPC(primaryStage)));
    NewWeekly.setOnAction(e-> primaryStage.setScene(newWeeklyPC(primaryStage)));
    NewDaily.setOnAction(e-> primaryStage.setScene(newDailyPC(primaryStage)));
    oldTotalSpending.setOnAction(e-> primaryStage.setScene(oldSpendingPC(primaryStage)));
    newTotalSpending.setOnAction(e-> primaryStage.setScene(newSpendingPC(primaryStage)));
    
    sceneTwoBP.setLeft(left3);
    sceneTwoBP.setCenter(mid3);
    sceneTwoBP.setRight(right3);
    return SceneTwo;
  }
  private Scene ChooseFileScene(Stage primaryStage) {
    //Creates a scene where the user has a prompt and button allowing them to load a file into the GUI
    BorderPane fileBP = new BorderPane();
    Scene fileScene = new Scene(fileBP, WINDOW_WIDTH, WINDOW_HEIGHT);
    VBox getFileTop = new VBox();
    getFileTop.setAlignment(Pos.CENTER);
    Label directions = new Label("Click The button below to choose your budget Data!");
    directions.setFont(new Font("Ariel", 30));
    Label directions2 = new Label("\n" + "Click the home button when finished!");
    directions2.setFont(new Font("Ariel", 24));
    VBox getFileMid = new VBox();
    getFileMid.setAlignment(Pos.CENTER);
    getFileMid.setPadding(new Insets(30, 30, 30, 30));
    Button loadFile = new Button("Load Data");
    loadFile.setShape(new Circle(200));
    loadFile.setMinSize(200, 200);
    loadFile.setMaxSize(200, 200);
    
    getFileMid.getChildren().add(loadFile);
    getFileTop.getChildren().addAll(directions, toolBar(primaryStage));
    fileBP.setTop(getFileTop);
    fileBP.setCenter(getFileMid);
    //creates a file selector for the user to choose data
    loadFile.setOnAction(e-> loadData(primaryStage));
   
    

    
    return fileScene; 
  }
  
  private void loadData(Stage primaryStage) {
    //method that creates a file chooser and then passes the selected file to the calculation class
    FileChooser fileChooser = new FileChooser();
    File FileName = fileChooser.showOpenDialog(primaryStage);
    try {
      ca.populateArrays(FileName);
      ca.calculate();
    } catch(Exception e) {
      
    }
  }
   private Scene oldMonthlyPC(Stage primaryStage) {
     //Creates a pie chart containing the Old Monthly budget data
     BorderPane overallBP = new BorderPane();
     Scene oldOverallPC = new Scene(overallBP, WINDOW_WIDTH, WINDOW_HEIGHT);
     
     @SuppressWarnings("rawtypes")
     String[] keys = ca.getMonthlyKeys();
     double[] values = ca.getMonthlyValues();
     
     for(int i = 0; i < keys.length; ++i) {
       keys[i] = keys[i] + " " + values[i];
     }
     
     PieChart.Data data[] = new PieChart.Data[keys.length];
     for(int i = 0; i < data.length; ++i) {
       data[i] = new PieChart.Data(keys[i], values[i]);
     }
     PieChart pieChart = new PieChart(FXCollections.observableArrayList(data));
     
     VBox vbox = new VBox(pieChart);
     VBox top = new VBox(toolBar(primaryStage));
     overallBP.setTop(top);
     overallBP.setCenter(vbox); 
     return oldOverallPC;
     
   }
   private Scene oldWeeklyPC(Stage primaryStage) {
   //Creates a pie chart containing the Old Weekly budget data
     BorderPane oldWeeklyBP = new BorderPane();
     Scene oldWeeklyPC = new Scene(oldWeeklyBP, WINDOW_WIDTH, WINDOW_HEIGHT);
     
     @SuppressWarnings("rawtypes")
     String[] keys = ca.getWeeklyKeys();
     double[] values = ca.getWeeklyValues();
     
     for(int i = 0; i < keys.length; ++i) {
       keys[i] = keys[i] + " " + values[i];
     }
     
     PieChart.Data data[] = new PieChart.Data[keys.length];
     for(int i = 0; i < data.length; ++i) {
       data[i] = new PieChart.Data(keys[i], values[i]);
     }
     PieChart pieChart = new PieChart(FXCollections.observableArrayList(data));
     
     VBox vbox = new VBox(pieChart);
     VBox top = new VBox(toolBar(primaryStage));
     oldWeeklyBP.setTop(top);
     oldWeeklyBP.setCenter(vbox); 
     return oldWeeklyPC;
     
   }
   private Scene oldDailyPC(Stage primaryStage) {
     //Creates a pie chart containing the Old Daily budget data
     BorderPane dailyBP = new BorderPane();
     Scene oldDailyPC = new Scene(dailyBP, WINDOW_WIDTH, WINDOW_HEIGHT);
     
     @SuppressWarnings("rawtypes")
     String[] keys = ca.getDailyKeys();
     double[] values = ca.getDailyValues();
     
     for(int i = 0; i < keys.length; ++i) {
       keys[i] = keys[i] + " " + values[i];
     }
     
     PieChart.Data data[] = new PieChart.Data[keys.length];
     for(int i = 0; i < data.length; ++i) {
       data[i] = new PieChart.Data(keys[i], values[i]);
     }
     PieChart pieChart = new PieChart(FXCollections.observableArrayList(data));
     
     VBox vbox = new VBox(pieChart);
     VBox top = new VBox(toolBar(primaryStage));
     dailyBP.setTop(top);
     dailyBP.setCenter(vbox); 
     return oldDailyPC;
     
   }
   private Scene newMonthlyPC(Stage primaryStage) {
     //Creates a pie chart containing the New Monthly budget data
     BorderPane newMonthlyBP = new BorderPane();
     Scene newMonthlyPC = new Scene(newMonthlyBP, WINDOW_WIDTH, WINDOW_HEIGHT);
     
     @SuppressWarnings("rawtypes")
     String[] keys = ca.getNewMonthlyKeys();
     double[] values = ca.getNewMonthlyValues();
     
     for(int i = 0; i < keys.length; ++i) {
       keys[i] = keys[i] + " " + values[i];
     }
     
     PieChart.Data data[] = new PieChart.Data[keys.length];
     for(int i = 0; i < data.length; ++i) {
       data[i] = new PieChart.Data(keys[i], values[i]);
     }
     PieChart pieChart = new PieChart(FXCollections.observableArrayList(data));
     
     VBox vbox = new VBox(pieChart);
     VBox top = new VBox(toolBar(primaryStage));
     newMonthlyBP.setTop(top);
     newMonthlyBP.setCenter(vbox); 
     return newMonthlyPC; 
     
   }
   private Scene newWeeklyPC(Stage primaryStage) {
   //Creates a pie chart containing the New Weekly budget data
     BorderPane newWeeklyBP = new BorderPane();
     Scene newWeeklyPC = new Scene(newWeeklyBP, WINDOW_WIDTH, WINDOW_HEIGHT);
     
     @SuppressWarnings("rawtypes")
     String[] keys = ca.getNewWeeklyKeys();
     double[] values = ca.getNewWeeklyValues();
     
     for(int i = 0; i < keys.length; ++i) {
       keys[i] = keys[i] + " " + values[i];
     }
     
     PieChart.Data data[] = new PieChart.Data[keys.length];
     for(int i = 0; i < data.length; ++i) {
       data[i] = new PieChart.Data(keys[i], values[i]);
     }
     PieChart pieChart = new PieChart(FXCollections.observableArrayList(data));
     
     VBox vbox = new VBox(pieChart);
     VBox top = new VBox(toolBar(primaryStage));
     newWeeklyBP.setTop(top);
     newWeeklyBP.setCenter(vbox); 
     return newWeeklyPC;  
     
   }
   private Scene newDailyPC(Stage primaryStage) {
     //Creates a pie chart containing the New Daily budget data
     BorderPane newDailyBP = new BorderPane();
     Scene newWeeklyPC = new Scene(newDailyBP, WINDOW_WIDTH, WINDOW_HEIGHT);
     
     @SuppressWarnings("rawtypes")
     String[] keys = ca.getNewDailyKeys();
     double[] values = ca.getNewDailyValues();
     
     for(int i = 0; i < keys.length; ++i) {
       keys[i] = keys[i] + " " + values[i];
     }
     
     PieChart.Data data[] = new PieChart.Data[keys.length];
     for(int i = 0; i < data.length; ++i) {
       data[i] = new PieChart.Data(keys[i], values[i]);
     }
     PieChart pieChart = new PieChart(FXCollections.observableArrayList(data));
     
     VBox vbox = new VBox(pieChart);
     VBox top = new VBox(toolBar(primaryStage));
     newDailyBP.setTop(top);
     newDailyBP.setCenter(vbox); 
     return newWeeklyPC;
     
   }
   private Scene oldSpendingPC(Stage primaryStage) {
     //Creates a pie chart containing the old budget spending data
     BorderPane OldSpendingBP = new BorderPane();
     Scene OldSpendingPC = new Scene(OldSpendingBP, WINDOW_WIDTH, WINDOW_HEIGHT);
     PieChart pieChart = new PieChart();
     String dataString = "Old Spending " + ca.getTotalSpending();
     PieChart.Data data = new PieChart.Data(dataString , ca.getTotalSpending());
     pieChart.getData().add(data);
     VBox vbox = new VBox(pieChart);
     VBox top = new VBox(toolBar(primaryStage));
     OldSpendingBP.setTop(top);
     OldSpendingBP.setCenter(vbox);
     return OldSpendingPC;
   }
   private Scene newSpendingPC(Stage primaryStage) {
     //Creates a pie chart containing the New budget spending data
     BorderPane NewSpendingBP = new BorderPane();
     Scene NewSpendingPC = new Scene(NewSpendingBP, WINDOW_WIDTH, WINDOW_HEIGHT);
     PieChart pieChart = new PieChart();
     String dataString = "New Spending " + ca.getNewTotalSpending();
     PieChart.Data data = new PieChart.Data(dataString , ca.getNewTotalSpending());
     pieChart.getData().add(data);
     VBox vbox = new VBox(pieChart);
     VBox top = new VBox(toolBar(primaryStage));
     NewSpendingBP.setTop(top);
     NewSpendingBP.setCenter(vbox);
     return NewSpendingPC;
   }
   
   private ToolBar toolBar(Stage primaryStage) {
     //creates a toolbar with a home and exit button
     ToolBar toolBar = new ToolBar();
     Button home = new Button("Home");
     home.setOnAction(e-> primaryStage.setScene(createMainScene(primaryStage)));
     Button exit = new Button("Exit");
     exit.setOnAction(e-> System.exit(0));
     toolBar.getItems().addAll(exit, home);
     return toolBar;
   }
  
  /**
   * launches the GUI
   * @param args
   */
  public static void main(String[] args) {
    launch(args);

  }

}
