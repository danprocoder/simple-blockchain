package com.test.blockchain;

import java.util.ArrayList;

public class Blockchain {
    private ArrayList<Block> chainList = new ArrayList<Block>();

    private short miningDifficulty = 1;

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

    public String getLastHash() {
        int size = this.chainList.size();
        if (size < 1) {
            return "";
        }

        return this.chainList.get(size - 1).getHash();
    }
}
