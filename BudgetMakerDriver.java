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
  static int Income = 0;
  private File csvFile;
  private ArrayList<BudgetNode> Monthly;
  private ArrayList<BudgetNode> Weekly;
  private ArrayList<BudgetNode> Daily;


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


  public static void main(String[] args) {


  }

}
