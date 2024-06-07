package com.test.blockchain;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.test.helper.SHA256;

public class Block {
    private final int index;

    private final String previousBlockHash;

    private final long timestamp;

    private final ArrayList<Transaction> transactionList = new ArrayList<Transaction>();

    private String hash;

    private int nonce = 0;

    public Block(int index, String previousBlockHash, long timestamp) {
        this.index = index;
        this.previousBlockHash = previousBlockHash;
        this.timestamp = timestamp;
    }

    public void addTransaction(Transaction transaction) {
        this.transactionList.add(transaction);
    }

    /**
     * Sets the nonce value.
     *
     * @param nonce
     */
    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public ArrayList<Transaction> getTransactions() {
        return this.transactionList;
    }

    public int getIndex() {
        return this.index;
    }

    public int getNonce() {
        return this.nonce;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String getPreviousHash() {
        return this.previousBlockHash;
    }
    
    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getHash() {
        return this.hash;
    }

    /**
     * Computes a has for the block. Make sure previous block hash, timestamp, nonce and transactions are set first (if
     * they need to be included) before computing.
     *
     * @return the computed hash
     */
    public String computeHash() {
        String data = this.previousBlockHash + this.timestamp;

        for (Transaction trx: this.transactionList) {
            data += trx.toString();
        }

        data += this.nonce;

        return SHA256.hash(data);
    }

    public void proofOfWork() {
        do {
            this.nonce++;
            this.hash = this.computeHash();
        } while (!this.checkHash());
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("previousHash", this.getPreviousHash());
        json.addProperty("hash", this.getHash());
        json.addProperty("nonce", this.getNonce());
        json.addProperty("timestamp", this.getTimestamp());

        JsonArray trxArray = new JsonArray();
        for (Transaction trx: this.getTransactions()) {
            trxArray.add(trx.toJson());
        }
        json.add("transactions", trxArray);

        return json;
    }

    private boolean checkHash() {
        short difficulty = Blockchain.getInstance().getMiningDifficulty();

        StringBuilder target = new StringBuilder();
        for (int i = 0; i < difficulty; i++) {
            target.append('0');
        }
        return this.hash.startsWith(target.toString());
    }

}
