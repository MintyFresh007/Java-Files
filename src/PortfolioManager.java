/*
** Class Name: PortfolioManager 
** Author: Ronald Macedo
** Date Created: 04/27/2026
** Purpose: To display interactive stock menu and past transactions.
*/
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PortfolioManager {

    private static ArrayList<TransactionHistory> portfolioList = new ArrayList<TransactionHistory>();

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            printMenu();

            if (scnr.hasNextInt()) {
                choice = scnr.nextInt();
                scnr.nextLine();

                if (choice == 1) {
                    depositCash(scnr);
                }
                else if (choice == 2) {
                    withdrawCash(scnr);
                }
                else if (choice == 3) {
                    buyStock(scnr);
                }
                else if (choice == 4) {
                    sellStock(scnr);
                }
                else if (choice == 5) {
                    displayTransactionHistory();
                }
                else if (choice == 6) {
                    displayPortfolio();
                }
                else if (choice == 0) {
                    System.exit(0);
                }
                else {
                    System.out.println("ERROR: Please enter a number from 0 to 6.");
                }
            }
            else {
                System.out.println("ERROR: Please enter a number.");
                scnr.nextLine();
            }
        }

        scnr.close();
    }

    public static void printMenu() {
        System.out.println();
        System.out.println();
        System.out.println("Ronald Macedo's Brokerage Account");
        System.out.println();
        System.out.println();
        System.out.println("0 - Exit");
        System.out.println();
        System.out.println("1 - Deposit Cash");
        System.out.println();
        System.out.println("2 - Withdraw Cash");
        System.out.println();
        System.out.println("3 - Buy Stock");
        System.out.println();
        System.out.println("4 - Sell Stock");
        System.out.println();
        System.out.println("5 - Display Transaction History");
        System.out.println();
        System.out.println("6 - Display Portfolio");
        System.out.println();
        System.out.print("Enter option (0 to 6): ");
    }

    public static void depositCash(Scanner scnr) {
        System.out.print("Enter date: ");
        String date = scnr.nextLine();

        System.out.print("Enter deposit amount: ");
        double amount = scnr.nextDouble();
        scnr.nextLine();

        if (amount <= 0) {
            System.out.println("ERROR: Amount must be positive.");
        }
        else {
            portfolioList.add(new TransactionHistory("CASH", date, "DEPOSIT", amount, 1.00));
            System.out.println("Money deposited.");
        }
    }

    public static void withdrawCash(Scanner scnr) {
        System.out.print("Enter date: ");
        String date = scnr.nextLine();

        System.out.print("Enter withdraw amount: ");
        double amount = scnr.nextDouble();
        scnr.nextLine();

        if (amount <= 0) {
            System.out.println("ERROR: Amount must be positive.");
        }
        else if (amount > getCashBalance()) {
            System.out.println("ERROR: Not enough money available.");
        }
        else {
            portfolioList.add(new TransactionHistory("CASH", date, "WITHDRAW", -amount, 1.00 ));
        }
    }

    public static void buyStock(Scanner scnr) {
        System.out.print("Enter date: ");
        String date = scnr.nextLine();

        System.out.print("Enter stock's ticker: ");
        String ticker = scnr.nextLine().toUpperCase();

        System.out.print("Enter quantity: ");
        double qty = scnr.nextDouble();

        System.out.print("Enter the price per stock: ");
        double price = scnr.nextDouble();
        scnr.nextLine();

        double totalCost = qty * price;

        if (qty <= 0 || price <= 0) {
            System.out.println("ERROR: Both quantity and price must be positive.");
        }
        else if (totalCost > getCashBalance()) {
            System.out.println("ERROR: Not enough to buy this stock.");
        }
        else {
            portfolioList.add(new TransactionHistory(ticker, date, "BUY", qty, price));
            portfolioList.add(new TransactionHistory("CASH", date, "WITHDRAW", -totalCost, 1.00));
            System.out.println("Stock bought.");
        }
    }

    public static void sellStock(Scanner scnr) {
        System.out.print("Enter date: ");
        String date = scnr.nextLine();

        System.out.print("Enter stock's ticker: ");
        String ticker = scnr.nextLine().toUpperCase();

        System.out.print("Enter quantity: ");
        double qty = scnr.nextDouble();

        System.out.print("Enter the price per stock: ");
        double price = scnr.nextDouble();
        scnr.nextLine();

        if (qty <= 0 || price <= 0) {
            System.out.println("ERROR: Both quantity and price must be positive.");
        }
        else if (qty > getStockBalance(ticker)) {
            System.out.println("ERROR: Not enough shares to sell.");
        }
        else {
            double totalSale = qty * price;

            portfolioList.add(new TransactionHistory(ticker, date, "SELL", qty, price));
            portfolioList.add(new TransactionHistory("CASH", date, "DEPOSIT", totalSale, 1.00));
            System.out.println("Stock sold.");
        }
    }

    public static void displayTransactionHistory() {
        System.out.println();
        System.out.printf("%49s\n", "Ronald Macedo's Brokerage Account");
        System.out.printf("%49s\n", "=================================");
        System.out.println();
        System.out.printf("%-15s %-8s %-15s %-15s %-10s\n", "Date", "Ticker", "Quantity", "Cost Basis", "Trans Type");
        System.out.println();
        System.out.println("===================================================================");
        System.out.println();

        for (int i = 0; i < portfolioList.size(); i++) {
            portfolioList.get(i).toPrint();
        }
    }

    public static void displayPortfolio() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

        System.out.println();
        System.out.println("Portfolio as of: " + LocalDateTime.now().format(format));
        System.out.println();
        System.out.println("====================================");
        System.out.println();
        System.out.printf("%-8s %-10s\n", "Ticker", "Quantity");
        System.out.println();
        System.out.println("==================");
        System.out.println();

        double cash = getCashBalance();

        if (cash != 0) {
            System.out.printf("%-8s %.1f\n\n", "CASH", cash);
        }

        ArrayList<String> printedTickers = new ArrayList<String>();

        for (int i = 0; i < portfolioList.size(); i++) {
            String ticker = portfolioList.get(i).getTicker();

            if (!ticker.equals("CASH") && !printedTickers.contains(ticker)) {
                double stockQty = getStockBalance(ticker);

                if (stockQty > 0) {
                    System.out.printf("%-8s %.1f\n\n", ticker, stockQty);
                }

                printedTickers.add(ticker);
            }
        }
    }

    public static double getCashBalance() {
        double cash = 0.0;

        for (int i = 0; i < portfolioList.size(); i++) {
            TransactionHistory transH = portfolioList.get(i);

            if (transH.getTicker().equals("CASH")) {
                cash = cash + transH.getQty();
            }
        }

        return cash;
    }

    public static double getStockBalance(String ticker) {
        double stockQty = 0.0;

        for (int i = 0; i < portfolioList.size(); i++) {
            TransactionHistory transH = portfolioList.get(i);

            if (transH.getTicker().equals(ticker)) {
                if (transH.getTransType().equals("BUY")) {
                    stockQty = stockQty + transH.getQty();
                }
                else if (transH.getTransType().equals("SELL")) {
                    stockQty = stockQty - transH.getQty();
                }
            }
        }
        return stockQty;
    }
}