package com.test.miner;

import java.text.DecimalFormat;

import com.test.Wallet;
import com.test.blockchain.Block;
import com.test.blockchain.Blockchain;
import com.test.blockchain.Transaction;
import com.test.helper.SHA256;

public class Miner {
    public static Miner instance;

    private double transactionFee = 2;
    private double miningReward = 2;

    public static Miner getInstance() {
        if (instance == null) {
            instance = new Miner();
        }

        return instance;
    }

    public void generateBlock(Transaction transaction, MineListener listener) {
        Blockchain blockchain = Blockchain.getInstance();

        try {
            Block newBlock = new Block(blockchain.getLastBlock().getHash(),  System.currentTimeMillis());
            newBlock.addTransaction(this.getCoinbaseTransaction());
            newBlock.addTransaction(transaction);
            this.blk(newBlock);
    
            listener.onBlockMined(newBlock);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private boolean checkHash(String hash) {
        int difficulty = Blockchain.getInstance().getMiningDifficulty();
    
        StringBuilder target = new StringBuilder();
        for (int i = 0; i < difficulty; i++) {
            target.append('0');
        }
        return hash.startsWith(target.toString());
    }
    
    public void blk(Block block) throws Exception {
        int nonce = 0;
        String hash;
        do {
            block.setNonce(nonce++);
            hash = block.computeHash();
        } while (!this.checkHash(hash));

        block.setHash(hash);
    }

    /**
     * Returns a transaction object representing the miner's reward.
     *
     * @return
     * @throws Exception
     */
    private Transaction getCoinbaseTransaction() throws Exception {
        long timestamp = System.currentTimeMillis();
        double amount = this.miningReward + this.transactionFee;
        String trxData = "Trx{from=, to=" + Wallet.getAddress() +
            ", amount=" + new DecimalFormat("0.#").format(amount) +
            ", timestamp=" + timestamp + "}";
        String signature = SHA256.signWithKey(trxData, Wallet.getSecretKey());

        return new Transaction(
            "",
            Wallet.getAddress(),
            amount,
            timestamp,
            signature
        );
    }
}
