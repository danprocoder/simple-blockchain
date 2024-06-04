package com.test.blockchain;

import java.util.ArrayList;
import com.test.helper.SHA256;

public class Block {
    private String previousBlockHash;

    private long timestamp;

    private String hash;

    private int nonce = 0;

    private ArrayList<Transaction> transactionList = new ArrayList<Transaction>();

    public Block(String previousBlockHash, long timestamp) {
        this.previousBlockHash = previousBlockHash;
        this.timestamp = timestamp;
    }

    public void addTransaction(Transaction transaction) {
        this.transactionList.add(transaction);
    }

    public String getHash() {
        return this.hash;
    }

    public String computeHash() {
        String data = this.previousBlockHash + Long.toString(this.timestamp);

        for (Transaction trx: this.transactionList) {
            data += trx.toString();
        }

        data += Integer.toString(this.nonce);

        return SHA256.hash(data);
    }

    public void proofOfWork() {
        while (!this.checkHash()) {
            this.nonce++;
            this.hash = this.computeHash();
        }
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
