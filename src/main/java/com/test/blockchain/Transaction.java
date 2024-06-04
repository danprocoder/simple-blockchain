package com.test.blockchain;

public class Transaction {
    private final String fromAddress;

    private final String toAddress;

    private final double amount;

    public Transaction(String fromAddress, String toAddress, double amount) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.amount = amount;
    }

    @Override()
    public String toString() {
        return "from=" + this.fromAddress + " to=" + this.toAddress + " amount=" + this.amount;
    }
}
