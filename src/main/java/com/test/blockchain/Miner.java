package com.test.blockchain;

public class Miner {
    public static Miner instance;

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
            newBlock.addTransaction(transaction);
            newBlock.proofOfWork();
    
            listener.onBlockMined(newBlock);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
