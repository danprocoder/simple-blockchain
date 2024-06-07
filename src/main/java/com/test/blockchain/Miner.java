package com.test.blockchain;

import com.test.Wallet;
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
            Block newBlock = new Block(blockchain.getSize(), blockchain.getLastHash(), System.currentTimeMillis());
            newBlock.addTransaction(this.getCoinbaseTransaction());
            newBlock.addTransaction(transaction);
            newBlock.proofOfWork();
    
            listener.onBlockMined(newBlock);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a transaction object representing the miner's reward.
     *
     * @return
     * @throws Exception
     */
    private Transaction getCoinbaseTransaction() throws Exception {
        long timestamp = System.currentTimeMillis();
        String trxData = "Trx{from=, to=" + Wallet.getAddress() + ", amount=0, timestamp=" + timestamp + "}";
        String signature = SHA256.signWithKey(trxData, Wallet.getSecretKey());

        return new Transaction(
            "",
            Wallet.getAddress(),
            this.miningReward + this.transactionFee,
            timestamp,
            signature
        );
    }
}
