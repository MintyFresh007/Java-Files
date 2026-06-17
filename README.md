# Stock Portfolio Application

This is a simple Java console application that simulates a basic brokerage account. The program allows a user to deposit cash, withdraw cash, buy stocks, sell stocks, display transaction history, and view the current portfolio.

The following skills and concepts were used throughout this project:

* Object-Oriented Programming
* Classes and Objects
* Constructors
* Encapsulation
* ArrayLists
* Loops and Conditional Statements
* User Input Validation
* Financial Transaction Processing
* Java Console Applications

## Features

* Deposit cash into brokerage account
* Withdraw cash from brokerage account
* Buy stock if enough cash is available
* Sell stock if enough shares are available
* Automatically create CASH transactions when buying or selling stock
* Display full transaction history
* Display current portfolio balance
* Error handling for invalid menu input
* Error handling for insufficient cash or stock quantity

## Technologies Used

* Java
* Visual Studio Code
* Java Development Kit (JDK)

## Project Files

* TransactionHistory.java
* PortfolioManager.java

## TransactionHistory.java

This class stores information about each transaction, including:

* Ticker
* Transaction date
* Transaction type
* Quantity
* Cost basis

## PortfolioManager.java

This class contains the main program. It includes:

* Menu system
* ArrayList of transaction records
* Deposit, withdraw, buy, and sell methods
* Transaction history display
* Portfolio display


## Prerequisites

Before running this project, make sure you have the following installed:

* Java Development Kit, JDK 17 or newer
* Visual Studio Code
* Java Extension Pack for Visual Studio Code

## How to Access and Run the Project

### 1. Download the Project

Click the green Code button, then choose Download ZIP.

### 2. Open the Folder in Visual Studio Code

* Open Visual Studio Code
* From there, click on File, then Open Folder
* Then select the extracted project folder

### 3. Confirm the Java Files Are Present

Make sure these two files are visible:

* TransactionHistory.java
* PortfolioManager.java

### 4. Run the Program

* Open PortfolioManager.java
* Click the Run button in Visual Studio Code
* Or right-click inside the file and select "Run Java"

### 5. Use the Menu

The program will display a menu like this:

0 - Exit

1 - Deposit Cash

2 - Withdraw Cash

3 - Buy Stock

4 - Sell Stock

5 - Display Transaction History

6 - Display Portfolio

Enter option (0 to 6):

---

#### Example Run

Input the following:

1

7/1/2026

10000

3

9/20/2026

DFEN

100

15

5

6

The transaction history should show:

```text
7/1/2026    CASH    10000.0    $1.0     DEPOSIT

9/20/2026   DFEN    100.0      $15.0    BUY

9/20/2026   CASH    -1500.0    $1.0     WITHDRAW
```

The portfolio should show:

```text
CASH    8500.0

DFEN    100.0
```
