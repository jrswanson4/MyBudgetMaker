package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



/**
 * This class is a driver behind the WelcomeFX.java GUI
 * This populates multiple ArrayLists that contain the data from a user
 * entered CSV file
 * This then takes the data entered and reformats it into a new Budget for the user
 * 
 * @author jerem
 *
 */
public class CreateArrays {
  private static ArrayList<BudgetNode> Monthly;
  private static ArrayList<BudgetNode> Weekly;
  private static ArrayList<BudgetNode> Daily;
  private static ArrayList<BudgetNode> newMonthly;
  private static ArrayList<BudgetNode> newWeekly;
  private static ArrayList<BudgetNode> newDaily;
  private static Integer Income;
  private static String numInHouse;
  private static String kidsUnderTwelve;
  static int totalSpending;
  static int newTotalSpending;
/**
 * Class that creates the internal node structure that stores data from the CSV file inputed
 * 
 * @author jerem
 *
 * @param <String>
 * @param <Integer>
 */
  class BudgetNode<String, Integer> {
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
   * Constructor that creates instances of the ArrayLists to store data
   */
  public CreateArrays() {
    Monthly = new ArrayList<BudgetNode>();
    Weekly = new ArrayList<BudgetNode>();
    Daily = new ArrayList<BudgetNode>();
    
  }
  /**
   * Method that populates the ArrayLists in the constructor with the data from the user entered CSV file
   * 
   * @param fileName
   * @throws FileNotFoundException
   */
  public void populateArrays(File fileName) throws FileNotFoundException {
    Scanner fileScanner = new Scanner(fileName);
    //Scans the first line of the CSV to get the income of the user
    if (fileScanner.hasNextLine()) {
      String line = fileScanner.nextLine();
      Scanner lineScanner = new Scanner(line);
      if (lineScanner.hasNext()) {
        Income = Integer.parseInt(lineScanner.next().strip());
      }
      
      lineScanner.close();
    }
    //Scans the second line of the CSV to populate the monthly expenses ArrayList
    if (fileScanner.hasNextLine()) {
      String line = fileScanner.nextLine();
      Scanner lineScanner = new Scanner(line);
      lineScanner.useDelimiter(",");
      while (lineScanner.hasNext()) {
        String key = lineScanner.next();
        Integer value = Integer.parseInt(lineScanner.next().strip());
        BudgetNode<String, Integer> newNode = new BudgetNode<String, Integer>(key, value);
        Monthly.add(newNode);
      }
      
      lineScanner.close();
    }
    //Scans the third Line of the CSV to populate the weekly expenses ArrayList
    if (fileScanner.hasNextLine()) {
      String line = fileScanner.nextLine();
      Scanner lineScanner = new Scanner(line);
      lineScanner.useDelimiter(",");
      while (lineScanner.hasNext()) {
        String key = lineScanner.next();
        Integer value = Integer.parseInt(lineScanner.next().strip());
        BudgetNode<String, Integer> newNode = new BudgetNode<String, Integer>(key, value);
        Weekly.add(newNode);
      }
      
      lineScanner.close();
    }
    //Scans the fourth line of the CSV
    if (fileScanner.hasNextLine()) {
      String line = fileScanner.nextLine();
      Scanner lineScanner = new Scanner(line);
      lineScanner.useDelimiter(",");
      while (lineScanner.hasNext()) {
        String key = lineScanner.next();
        Integer value = Integer.parseInt(lineScanner.next().strip());
        BudgetNode<String, Integer> newNode = new BudgetNode<String, Integer>(key, value);
        Daily.add(newNode);
      }
      
      lineScanner.close();
    }
    //Scans the Fifth line of the CSV to get the number of people in the house hold
    if(fileScanner.hasNextLine()) {
      String line = fileScanner.nextLine();
      numInHouse = line;
    }
    //Scans the Sixth line of the CSV to get the number of kids under 12 in the house hold
    if(fileScanner.hasNextLine()) {
      String line = fileScanner.nextLine();
      kidsUnderTwelve = line;
    }
    fileScanner.close();
  }
  /**
   * Performs the calculation of the new Budget and assigns the new Budget to its own separate ArrayLists
   */
  public void calculate() {
    newMonthly = new ArrayList<BudgetNode>();
    newWeekly = new ArrayList<BudgetNode>();
    newDaily = new ArrayList<BudgetNode>();
    
    //For loops that populate the new ArrayLists with the data of the old budget
    for(int i = 0; i < Monthly.size(); ++i) {
      newMonthly.add(Monthly.get(i));
    }
    for(int i = 0; i < Weekly.size(); ++i) {
      newWeekly.add(Weekly.get(i));
    }
    for(int i = 0; i < Daily.size(); ++i) {
      newDaily.add(Daily.get(i));
    }
    
    //For loops that get the total amount of money spent in a month by the user
    for (int i = 0; i < newMonthly.size(); ++i) {
      totalSpending += (Integer) newMonthly.get(i).getValue();
    }
    for (int i = 0; i < newWeekly.size(); ++i) {
      totalSpending += 4 * (Integer) newWeekly.get(i).getValue();
    }
    for (int i = 0; i < newDaily.size(); ++i) {
      totalSpending += 20 * (Integer) newDaily.get(i).getValue();
    }
    

    // if statement to check if spending is more than monthly income, if it is not is will return
    // with message saying there is no need to save
    if (totalSpending < Income) {
      return;
    }
    // empties the daily cost array as there is no need for anyone to spend money on things daily
    for (int i = newDaily.size() - 1; i >= 0; --i) {
      newDaily.remove(i);
    }
    newDaily.add(new BudgetNode<String, Integer>("Nothing", 0));

    // for loop to lower the budget for groceries to the national average for a household of the
    // users size
    for (int i = 0; i < newWeekly.size(); ++i) {
      if (newWeekly.get(i).getKey().toString().toLowerCase().equals("groceries")) {
        BudgetNode groceryNode = newWeekly.get(i);
        newWeekly.get(i).getValue();
        newWeekly.remove(i);
        newWeekly.add(i, groceryHelper(groceryNode));
        
      }
    }
    // for loop to lower the budget for daycare down to the national average for daycare for the
    // number of kids described
    for (int i = 0; i < newWeekly.size(); ++i) {
      if (newWeekly.get(i).getKey().toString().toLowerCase().equals("daycare")) {
        BudgetNode daycareNode = newWeekly.get(i);
        System.out.println(newWeekly.get(i).getValue());
        newWeekly.remove(i);
        newWeekly.add(i, daycareHelper(daycareNode));
        
      }
    }
    // for loop to lower the budget for utilities by an average of $50 for practicing better habits
    // of utility use
    for (int i = 0; i < newMonthly.size(); ++i) {
      if (newMonthly.get(i).getKey().toString().toLowerCase().equals("utilities")) {
        BudgetNode utilitiesNode = newMonthly.get(i);
        System.out.println(newMonthly.get(i).getValue());
        newMonthly.remove(i);
        newMonthly.add(i, utilitiesHelper(utilitiesNode));
        
      }
    }
    // for loop to lower the budget of a phone bill to the lowest possible plan for the number of
    // phones needed
    // This assumes children under 12 are not going to have a phone in the phone plan
    for (int i = 0; i < newMonthly.size(); ++i) {
      if (newMonthly.get(i).getKey().toString().toLowerCase().equals("phone")) {
        BudgetNode phoneNode = newMonthly.get(i);
        System.out.println(newMonthly.get(i).getValue());
        newMonthly.remove(i);
        newMonthly.add(i, phoneHelper(phoneNode));
        
      }
    }
    
    for (int i = 0; i < newMonthly.size(); ++i) {
      newTotalSpending += (Integer) newMonthly.get(i).getValue();
    }
    for (int i = 0; i < newWeekly.size(); ++i) {
      newTotalSpending += 4 * (Integer) newWeekly.get(i).getValue();
    }
    for (int i = 0; i < newDaily.size(); ++i) {
      newTotalSpending += 20 * (Integer) newDaily.get(i).getValue();
    }
    

  }
  private BudgetNode phoneHelper(BudgetNode phoneNode) {
    int numberOfLines = Integer.parseInt(numInHouse) - Integer.parseInt(kidsUnderTwelve);
    BudgetNode<String, Integer> newPhoneNode = null;
    //This section calculates a phone bill based on the number of people using a phone in the house
    if (numberOfLines == 1) {
      if ((Integer) phoneNode.getValue() > 90) {
        newPhoneNode = new BudgetNode<String, Integer>("phone", 90);
      } else {
        return phoneNode;
      }
    }
    if (numberOfLines == 2) {
      if ((Integer) phoneNode.getValue() > 150) {
        newPhoneNode = new BudgetNode<String, Integer>("phone", 150);
      } else {
        return phoneNode;
      }
    }
    if (numberOfLines == 3) {
      if ((Integer) phoneNode.getValue() > 195) {
        newPhoneNode = new BudgetNode<String, Integer>("phone", 195);
      } else {
        return phoneNode;
      }
    }
    if (numberOfLines == 4) {
      if ((Integer) phoneNode.getValue() > 240) {
        newPhoneNode = new BudgetNode<String, Integer>("phone", 240);
      } else {
        return phoneNode;
      }
    }
    if (numberOfLines == 5) {
      if ((Integer) phoneNode.getValue() > 285) {
        newPhoneNode = new BudgetNode<String, Integer>("phone", 285);
      } else {
        return phoneNode;
      }
    }
    if (numberOfLines == 6) {
      if ((Integer) phoneNode.getValue() > 330) {
        newPhoneNode = new BudgetNode<String, Integer>("phone", 330);
      } else {
        return phoneNode;
      }
    }
    if (numberOfLines > 6) {
      if ((Integer) phoneNode.getValue() > ((25 * numberOfLines) + (30 * numberOfLines))) {
        newPhoneNode =
            new BudgetNode<String, Integer>("phone", ((25 * numberOfLines) + (30 * numberOfLines)));
      } else {
        return phoneNode;
      }
    }
    if (newPhoneNode == null) {
      return phoneNode;
    } else {
      return newPhoneNode;
    }

  }

  private BudgetNode utilitiesHelper(BudgetNode utilitiesNode) {
    //Subtracts a national average of $50 from the utilities based on normal energy saving techniques
    if ((Integer)utilitiesNode.getValue() > 100) {
      BudgetNode<String, Integer> newUtilitiesNode =
          new BudgetNode<String, Integer>("utilities", (Integer) utilitiesNode.getValue() - 50);
      return newUtilitiesNode;
    } else {
      return utilitiesNode;
    }
  }

  private BudgetNode daycareHelper(BudgetNode daycareNode) {
    //Refactors datcare cost based on national averages for day care costs per kid under 12
    BudgetNode<String, Integer> newDaycareNode = null;
    if ((Integer) daycareNode.getValue() > (242 * Integer.parseInt(kidsUnderTwelve))) {
      newDaycareNode =
          new BudgetNode<String, Integer>("daycare", (242 * Integer.parseInt(kidsUnderTwelve)));
      return newDaycareNode;
    } else {
      return daycareNode;
    }

  }

  private BudgetNode groceryHelper(BudgetNode groceryNode) {
    //Uses USDA averages to refactor Grocery Costs based on number of people in the house
    BudgetNode<String, Integer> newGroceryNode = null;
    if (Integer.parseInt(numInHouse) == 1) {
      newGroceryNode = new BudgetNode<String, Integer>("groceries", 59);
    }
    if (Integer.parseInt(numInHouse) == 2) {
      newGroceryNode = new BudgetNode<String, Integer>("groceries", 121);
    }
    if (Integer.parseInt(numInHouse) == 3) {
      newGroceryNode = new BudgetNode<String, Integer>("groceries", 156);
    }
    if (Integer.parseInt(numInHouse) == 4) {
      newGroceryNode = new BudgetNode<String, Integer>("groceries", 192);
    }
    if (Integer.parseInt(numInHouse) >= 5) {
      newGroceryNode = new BudgetNode<String, Integer>("groceries",
          (192 + (35 * Integer.parseInt(numInHouse) - 4)));
    }
    if (newGroceryNode == null) {
      return groceryNode;
    } else {
      return newGroceryNode;
    }

  } 
  /**
   * Returns a string array of the keys of the Monthly ArrayList
   * @return String[]
   */
  public String[] getMonthlyKeys(){
    String[] MonthlyKeys = new String[Monthly.size()];
    for(int i = 0; i < MonthlyKeys.length; ++i) {
      MonthlyKeys[i] = (String) Monthly.get(i).getKey();
    }
    return MonthlyKeys;
  }
  /**
   * returns a double array of the values of the Monthly ArrayList
   * @return double[]
   */
  public double[] getMonthlyValues() {
    double[] MonthlyValues = new double[Monthly.size()];
    for(int i = 0; i < MonthlyValues.length; ++i) {
      MonthlyValues[i] = (int) Monthly.get(i).getValue();
    }
    return MonthlyValues;  
  }
  /**
   * returns a string array of the keys of the Weekly ArrayList
   * @return String[]
   */
  public String[] getWeeklyKeys(){
    String[] WeeklyKeys = new String[Weekly.size()];
    for(int i = 0; i < WeeklyKeys.length; ++i) {
      WeeklyKeys[i] = (String) Weekly.get(i).getKey();
    }
    return WeeklyKeys;
  }
  /**
   * returns a double array of the values in the Monthly ArrayList
   * @return double[]
   */
  public double[] getWeeklyValues() {
    double[] WeeklyValues = new double[Weekly.size()];
    for(int i = 0; i < WeeklyValues.length; ++i) {
      WeeklyValues[i] = (int) Weekly.get(i).getValue(); 
    }
    return WeeklyValues;  
  }
  /**
   * returns a String array of the keys in the Daily ArrayList
   * @return String[]
   */
  public String[] getDailyKeys(){
    String[] DailyKeys = new String[Daily.size()];
    for(int i = 0; i < DailyKeys.length; ++i) {
      DailyKeys[i] = (String) Daily.get(i).getKey();
    }
    return DailyKeys;
  }
  /**
   * returns a double array of the values in the Daily ArrayList
   * @return double[]
   */
  public double[] getDailyValues() {
    double[] DailyValues = new double[Daily.size()];
    for(int i = 0; i < DailyValues.length; ++i) {
      DailyValues[i] = (int) Daily.get(i).getValue(); 
    }
    return DailyValues;  
  }
  /**
   * returns a string array of the keys in the newMonthly ArrayList
   * @return String[]
   */
  public String[] getNewMonthlyKeys(){
    String[] NewMonthlyKeys = new String[newMonthly.size()];
    for(int i = 0; i < NewMonthlyKeys.length; ++i) {
      NewMonthlyKeys[i] = (String) newMonthly.get(i).getKey();
    }
    return NewMonthlyKeys;
  }
  /**
   * returns a double array of the values in the newMonthly ArrayList
   * @return double[]
   */
  public double[] getNewMonthlyValues() {
    double[] NewMonthlyValues = new double[newMonthly.size()];
    for(int i = 0; i < NewMonthlyValues.length; ++i) {
      NewMonthlyValues[i] = (int) newMonthly.get(i).getValue();
    }
    return NewMonthlyValues;  
  }
  /**
   * returns a string array of the keys in the newWeekly ArrayList
   * @return String[]
   */
  public String[] getNewWeeklyKeys(){
    String[] NewWeeklyKeys = new String[newWeekly.size()];
    for(int i = 0; i < NewWeeklyKeys.length; ++i) {
      NewWeeklyKeys[i] = (String) newWeekly.get(i).getKey();
    }
    return NewWeeklyKeys;
  }
  /**
   * returns a double array of the values in the newWeekly ArrayList
   * @return double[]
   */
  public double[] getNewWeeklyValues() {
    double[] NewWeeklyValues = new double[newWeekly.size()];
    for(int i = 0; i < NewWeeklyValues.length; ++i) {
      NewWeeklyValues[i] = (int) newWeekly.get(i).getValue();
    }
    return NewWeeklyValues;  
  }
  /**
   * returns a string array of the keys in the newDaily ArrayList
   * @return String[]
   */
  public String[] getNewDailyKeys(){
    String[] NewDailyKeys = new String[newDaily.size()];
    for(int i = 0; i < NewDailyKeys.length; ++i) {
      NewDailyKeys[i] = (String) newDaily.get(i).getKey();
    }
    return NewDailyKeys; 
  }
  /**
   * returns a double array of the values in the newDaily ArrayList
   * @return double[]
   */
  public double[] getNewDailyValues() {
    double[] NewDailyValues = new double[newDaily.size()];
    for(int i = 0; i < NewDailyValues.length; ++i) {
      NewDailyValues[i] = (int) newDaily.get(i).getValue();
    }
    return NewDailyValues;  
  }
  /**
   * Getter for the total spending in the old budget
   * @return double
   */
  public double getTotalSpending() {
    return CreateArrays.totalSpending;
  }
  /**
   * Getter for the total spending in the new budget
   * @return double
   */
  public double getNewTotalSpending() {
    return CreateArrays.newTotalSpending;
  }
  
  

}
