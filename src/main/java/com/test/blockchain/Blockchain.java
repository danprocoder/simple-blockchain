package com.test.blockchain;

import java.util.ArrayList;

public class Blockchain {
    private final ArrayList<Block> chainList = new ArrayList<Block>();

    private short miningDifficulty = 4;

    private static Blockchain instance = null;

    private Blockchain() {}

    public static Blockchain getInstance() {
        if (Blockchain.instance == null) {
            Blockchain.instance = new Blockchain();
        }

        return Blockchain.instance;
    }

    public void addToBlockChain(Block block) throws Exception {
        if (!block.getHash().equals(block.computeHash())) {
            throw new Exception("Block hash mismatch");
        }

        this.chainList.add(block);
    }

    public short getMiningDifficulty() {
        return this.miningDifficulty;
    }

    public int getSize() {
        return this.chainList.size();
    }

    public String getLastHash() {
        int size = this.chainList.size();
        if (size < 1) {
            return "";
        }

        return this.chainList.get(size - 1).getHash();
    }

    public void printBlockchain() {
        for (Block block: this.chainList) {
            System.out.println("Block #" + block.getIndex());
            System.out.println("Previous Hash: " + block.getPreviousHash());
            System.out.println("Hash: " + block.getHash());
            System.out.println("Timestamp: " + block.getTimestamp());
            System.out.println();

            for (Transaction trx: block.getTransactions()) {
                System.out.println(trx.toString());
            }

            System.out.println();
        }
    }
}
