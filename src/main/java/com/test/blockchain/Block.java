package com.test.blockchain;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;

import com.test.Wallet;
import com.test.helper.SHA256;

public class Block {
    private final int index;

    private final String previousBlockHash;

    private final long timestamp;

    private final ArrayList<Transaction> transactionList = new ArrayList<Transaction>();

    private String hash;

    private int nonce = 0;

    private double transactionFee = 2;
    private double miningReward = 2;

    public Block(int index, String previousBlockHash, long timestamp) throws Exception {
        this.index = index;
        this.previousBlockHash = previousBlockHash;
        this.timestamp = timestamp;

        // Create coinbase transaction
        this.transactionList.add(this.getCoinbaseTransaction());
    }

    public void addTransaction(Transaction transaction) {
        this.transactionList.add(transaction);
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
        do {
            this.nonce++;
            this.hash = this.computeHash();
        } while (!this.checkHash());
    }

    private boolean checkHash() {
        short difficulty = Blockchain.getInstance().getMiningDifficulty();

        StringBuilder target = new StringBuilder();
        for (int i = 0; i < difficulty; i++) {
            target.append('0');
        }
        return this.hash.startsWith(target.toString());
    }

    private Transaction getCoinbaseTransaction() throws Exception {
        long timestamp = System.currentTimeMillis();
        String trxData = "Trx{from=, to=" + Wallet.getAddress() + ", amount=0, timestamp=" + timestamp + "}";
        String signature = this.signWithKey(trxData, Wallet.getSecretKey());

        return new Transaction(
            "",
            Wallet.getAddress(),
            this.miningReward + this.transactionFee,
            timestamp,
            signature
        );
    }

    private String signWithKey(String data, String key) throws Exception {
        PrivateKey privateKey = KeyFactory
            .getInstance("RSA")
            .generatePrivate(
                new X509EncodedKeySpec(key.getBytes())
            );

        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes());

        return Base64.getEncoder().encodeToString(signature.sign());
    }
}
