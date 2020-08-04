package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * 
 */

/**
 * @author jerem
 *
 */
public class BudgetMakerDriver {
  private int Income;
  private File csvFile;
  private ArrayList<BudgetNode> Monthly;
  private ArrayList<BudgetNode> Weekly;
  private ArrayList<BudgetNode> Daily;
  private ArrayList<BudgetNode> newMonthly;
  private ArrayList<BudgetNode> newWeekly;
  private ArrayList<BudgetNode> newDaily;
  private String numInHouse;
  private String kidsUnderTwelve;
  private String savingFor;
  private String saveHowMuch;
  private String saveHowLong;


  private class BudgetNode<String, Integer> {
    private String key;
    private Integer value;


    private BudgetNode(String key, Integer value) {
      this.key = key;
      this.value = value;

    }

    public String getKey() {
      return key;
    }

    public Integer getValue() {
      return value;
    }
  }

  public BudgetMakerDriver() {
    Monthly = new ArrayList<BudgetNode>();
    Weekly = new ArrayList<BudgetNode>();
    Daily = new ArrayList<BudgetNode>();
    csvFile = null;
  }

  public void budgetArrayInit() throws FileNotFoundException {
    Scanner fileScanner = new Scanner(csvFile);
    if(fileScanner.hasNextLine()) {
      String line= fileScanner.nextLine();
      Scanner lineScanner = new Scanner(line);
      if(lineScanner.hasNext()) {
        Income = Integer.parseInt(lineScanner.next().strip());
      }
      System.out.println(Income);
      lineScanner.close();
    }
    if(fileScanner.hasNextLine()) {
      String line = fileScanner.nextLine();
      Scanner lineScanner = new Scanner(line);
      lineScanner.useDelimiter(",");
      while (lineScanner.hasNext()) {
        String key = lineScanner.next();
        Integer value = Integer.parseInt(lineScanner.next().strip());
        BudgetNode<String, Integer> newNode = new BudgetNode<String, Integer>(key, value);
        Monthly.add(newNode);
      }
      for(int i = 0; i < Monthly.size(); ++i) {
        System.out.println(Monthly.get(i).getKey());
      }
      lineScanner.close();
    }
    System.out.println();
    if(fileScanner.hasNextLine()) {
      String line = fileScanner.nextLine();
      Scanner lineScanner = new Scanner(line);
      lineScanner.useDelimiter(",");
      while (lineScanner.hasNext()) {
        String key = lineScanner.next();
        Integer value = Integer.parseInt(lineScanner.next().strip());
        BudgetNode<String, Integer> newNode = new BudgetNode<String, Integer>(key, value);
        Weekly.add(newNode);
      }
      for(int i = 0; i < Weekly.size(); ++i) {
        System.out.println(Weekly.get(i).getKey());
      }
      lineScanner.close();
    }
    if(fileScanner.hasNextLine()) {
      String line = fileScanner.nextLine();
      Scanner lineScanner = new Scanner(line);
      lineScanner.useDelimiter(",");
      while (lineScanner.hasNext()) {
        String key = lineScanner.next();
        Integer value = Integer.parseInt(lineScanner.next().strip());
        BudgetNode<String, Integer> newNode = new BudgetNode<String, Integer>(key, value);
        Daily.add(newNode);
      }
      for(int i = 0; i < Weekly.size(); ++i) {
        System.out.println(Daily.get(i).getKey());
      }
      lineScanner.close();
    }
    fileScanner.close();

  }



  public void go(File csvFile) {
    this.csvFile = csvFile;
    try {
      budgetArrayInit();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
  
  public void setNumInHouse(String numInHouse) {
    this.numInHouse = numInHouse;
    System.out.println(this.numInHouse);
  }
  public void setKidsUnderTwelve(String kidsUnderTwelve) {
    this.kidsUnderTwelve = kidsUnderTwelve;
    System.out.println(this.kidsUnderTwelve);
  }
  public void setSavingFor(String savingFor) {
    this.savingFor = savingFor;
    System.out.println(this.savingFor);
  }
  public void setSavingHowMuch(String savingHowMuch) {
    this.saveHowMuch = savingHowMuch;
    System.out.println(this.saveHowMuch);
  }
  public void setSavingHowLong(String saveHowLong) {
    this.saveHowLong = saveHowLong;
    System.out.println(this.saveHowLong);
  }
  
  public void newBudgetMaker() {
    newMonthly = new ArrayList<BudgetNode>();
    newWeekly = new ArrayList<BudgetNode>();
    newDaily = new ArrayList<BudgetNode>();
    int totalSpending = 0;
    newMonthly = Monthly;
    newWeekly = Weekly;
    newDaily = Daily;
    
    for(int i = 0; i < newMonthly.size(); ++i) {
      totalSpending += (Integer) newMonthly.get(i).getValue();
    }
    for(int i = 0; i < newWeekly.size(); ++i) {
      totalSpending += 4 * (Integer) newWeekly.get(i).getValue();
    }
    for(int i = 0; i < newDaily.size(); ++i) {
      totalSpending += 20 * (Integer) newDaily.get(i).getValue();
    }
    System.out.println(totalSpending);
    
    //if statement to check if spending is more than monthly income, if it is not is will return with message saying there is no need to save
    if(totalSpending < Income) {
      return;
    }
    //empties the daily cost array as there is no need for anyone to spend money on things daily
    for(int i = newDaily.size() - 1; i >= 0; --i) {
      newDaily.remove(i);
    }
    System.out.println(newDaily.size());
    //for loop to lower the budget for groceries to the national average for a household of the users size
    for(int i = 0; i < newWeekly.size(); ++i) {
      if(newWeekly.get(i).getKey().toString().toLowerCase().equals("groceries")) {
        BudgetNode groceryNode = newWeekly.get(i);
        System.out.println(newWeekly.get(i).getValue());
        newWeekly.remove(i);
        newWeekly.add(i, groceryHelper(groceryNode));
        System.out.println(newWeekly.get(i).getValue());
      }
    }
    //for loop to lower the budget for daycare down to the national average for daycare for the number of kids described
    for(int i = 0; i < newWeekly.size(); ++i) {
      if(newWeekly.get(i).getKey().toString().toLowerCase().equals("daycare")) {
        BudgetNode daycareNode = newWeekly.get(i);
        System.out.println(newWeekly.get(i).getValue());
        newWeekly.remove(i);
        newWeekly.add(i, daycareHelper(daycareNode));
        System.out.println(newWeekly.get(i).getValue());
      }
    }
    
    
  }
  private BudgetNode daycareHelper(BudgetNode daycareNode) {
    BudgetNode<String, Integer> newDaycareNode = null;
    newDaycareNode = new BudgetNode<String, Integer>("daycare", (242 * Integer.parseInt(kidsUnderTwelve)));
    return newDaycareNode;
  }
  private BudgetNode groceryHelper(BudgetNode groceryNode) {
    BudgetNode<String, Integer> newGroceryNode = null;
    if(Integer.parseInt(numInHouse) == 1) {
      newGroceryNode = new BudgetNode<String, Integer>("groceries", 59);
    }
    if(Integer.parseInt(numInHouse) == 2) {
      newGroceryNode = new BudgetNode<String, Integer>("groceries", 121);
    }
    if(Integer.parseInt(numInHouse) == 3) {
      newGroceryNode = new BudgetNode<String, Integer>("groceries", 156);
    }
    if(Integer.parseInt(numInHouse) == 4) {
      newGroceryNode = new BudgetNode<String, Integer>("groceries", 192);
    }
    if(Integer.parseInt(numInHouse) >= 5) {
      newGroceryNode = new BudgetNode<String, Integer>("groceries", (192 + (35 * Integer.parseInt(numInHouse) - 4)));
    }
    if(newGroceryNode == null) {
      return groceryNode;
    }else {
      return newGroceryNode;
    }
    
  }


  public static void main(String[] args) {


  }

}
