# BudgetMaker

BudgetMaker is a java program whos goal is to refactor a users budget to save them money.

## Usage
The user will need to create a CSV file containing their financial information in the following way

The first line should be income by month, the second line should be monthy expenses, the third line should be weekly expenses, and the fourth should be daily expenses
In each line they should specify what the cost is (hulu, netflix, gas) followed by a comma followed by the cost with no dollar sign. 
After the budget data the user should then answer two questions:

How many people are in your Household?

How many kids under twelve do you have?

An example CSV file is included in the repository for you to look over. I suggest opening the file in a text editor like notepad rather than and excel sheet
this will give you a better understanding of what the correct format of the file should be.








There is not functionality yet if the CSV file is formatted incorrectly. When formatted correctly the user will be able load the file in through a fileselector
and then see different graphs of the processed data within the GUI.

An example file is included and can be chosen by the user!

## Contributing
I am not accepting pull requests at the moment as this is the shell of a project that is being worked on continuously.

## Credits
Numbers used for refactoring Grocery costs comes from USDA averages.
Numbers used for daycare costs comes from national daycare average.s
Numbers used for phone bill is based on the multi line essentials plan from T-Mobile.
Numbers used for Utility bill is based on normal energy saving techniques netting around $50 in savings each month.

## Future Work

Better exception handiling, such as if the user enters an incorrectly formatted csv file. This was not able to be done in the time alloted and is a feature I would like to add.
Added functionality to allow user to input data within the GUI about their own budget.
