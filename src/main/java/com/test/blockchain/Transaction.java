package com.test.blockchain;

public class Transaction {
    private final String fromAddress;

    private final String toAddress;

    private final Double amount;

    private final Double timestamp;

    private final String signature;

    public Transaction(String fromAddress, String toAddress, Double amount, Double timestamp, String signature) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.amount = amount;
        this.timestamp = timestamp;
        this.signature = signature;
    }

    @Override()
    public String toString() {
        return "Trx{from=" + this.fromAddress + ", to=" + this.toAddress + ", amount=" + this.amount + "}";
    }
}
