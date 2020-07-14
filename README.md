# MyBudgetMaker
This program will allow the user to enter their financial information, and then get feedback on how to better budget their money to save for a goal or just in general

The user will need to create a CSV file containing their financial information in the following way

The first line should be income, the second line should be monthy expenses, the third line should be weekly expenses, and the fourth should be daily expenses
In each line they should specify what the cost is (hulu, netflix, gas) followed by a comma followed by the cost with no dollar sign. 

Here is an example

Income,4800
Hulu,8.99,Netflix,9.99,Internet,49.99,Bills,460
Groceries,200,Gas,60,Daycare,1000
Coffee,5,Lunch,10

In this format the BudgetMaker will then take these expenses and after getting user responses to questions formulate a new budget to help the person save money
It will in turn output a CSV file that can be used in excel for the person to see the breakdown of their new budget.