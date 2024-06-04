package com.test.blockchain;

import java.util.ArrayList;

import com.test.helper.SHA256;

public class Block {
    private String previousBlockHash;

    private long timestamp;

    private String hash;

    private int nonce = 0;

    private int miningDifficulty = 1;

    private ArrayList<Transaction> transactionList = new ArrayList<Transaction>();

    public Block(String previousBlockHash, long timestamp, int miningDifficulty) {
        this.previousBlockHash = previousBlockHash;
        this.timestamp = timestamp;
        this.miningDifficulty = miningDifficulty;
    }

    public void addTransaction(Transaction transaction) {
        this.transactionList.add(transaction);
    }

    public String computeHash() {
        String data = this.previousBlockHash + Long.toString(this.timestamp);

        for (Transaction trx: this.transactionList) {
            data += trx.toString();
        }

        data += Integer.toString(this.nonce);

        return SHA256.hash(data);
    }
}
