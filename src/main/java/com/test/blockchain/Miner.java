package com.test.blockchain;

public class Miner {
    public static Miner instance;

    public static Miner getInstance() {
        if (instance == null) {
            instance = new Miner();
        }

        return instance;
    }

    public void onTransaction(Transaction transaction) {
        System.out.println("Transaction received: " + transaction.toString());

        Blockchain blockchain = Blockchain.getInstance();

        Block newBlock = new Block(blockchain.getLastHash(), System.currentTimeMillis() / 1000L);
        newBlock.addTransaction(transaction);
        newBlock.proofOfWork();

        try {
            blockchain.addToBlockChain(newBlock);
    
            System.out.println("Block added to blockchain: " + newBlock.getHash());
        } catch (Exception e) {
            System.out.println();
        }
    }
}
