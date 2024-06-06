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

        Block newBlock = new Block(blockchain.getSize(), blockchain.getLastHash(), System.currentTimeMillis() / 1000L);
        newBlock.addTransaction(transaction);
        newBlock.proofOfWork();

        listener.onBlockMined(newBlock);
    }
}
