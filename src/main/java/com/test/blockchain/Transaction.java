package com.test.blockchain;

import com.google.gson.JsonObject;

public class Transaction {
    private final String fromAddress;

    private final String toAddress;

    private final double amount;

    private final long timestamp;

    private final String signature;

    public Transaction(String fromAddress, String toAddress, double amount, long timestamp, String signature) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.amount = amount;
        this.timestamp = timestamp;
        this.signature = signature;
    }

    public String getFromAddress() {
        return this.fromAddress;
    }

    public String toAddress() {
        return this.toAddress;
    }

    public double getAmount() {
        return this.amount;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String getSignature() {
        return this.signature;
    }

    @Override()
    public String toString() {
        return "Trx{from=" + this.fromAddress + ", to=" + this.toAddress + ", amount=" + this.amount + ", timestamp=" + this.timestamp + "}";
    }

    public JsonObject toJson() {
        JsonObject trxObject = new JsonObject();

        trxObject.addProperty("from", this.fromAddress);
        trxObject.addProperty("to", this.toAddress);
        trxObject.addProperty("amount", this.amount);
        trxObject.addProperty("timestamp", this.timestamp);
        trxObject.addProperty("signature", this.signature);

        return trxObject;
    }
}
