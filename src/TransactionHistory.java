/*
** Class Name: TransactionHistory 
** Author: Ronald Macedo
** Date Created: 04/27/2026
** Purpose: To store different types of transactions.
*/
public class TransactionHistory {


    private String ticker;
    private String transDate;
    private String transType;
    private double qty;
    private double costBasis;

    public TransactionHistory() {
        ticker = "";
        transDate = "";
        transType = "";
        qty = 0.0;
        costBasis = 0.0;
    }

    public TransactionHistory(String ticker, String transDate, String transType, double qty, double costBasis) {
        this.ticker = ticker;
        this.transDate = transDate;
        this.transType = transType;
        this.qty = qty;
        this.costBasis = costBasis;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getCostBasis() {
        return costBasis;
    }

    public void setCostBasis(double costBasis) {
        this.costBasis = costBasis;
    }

    public void toPrint() {
        System.out.printf("%-15s %-8s %-15.1f $%-14.1f %-10s\n\n", transDate, ticker, qty, costBasis, transType);
        System.out.println();
    }
}
    
