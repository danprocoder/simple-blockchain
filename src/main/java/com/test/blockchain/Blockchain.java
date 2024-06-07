package com.test.blockchain;

import java.util.ArrayList;

public class Blockchain {
    private final ArrayList<Block> chainList = new ArrayList<Block>();

    private short miningDifficulty = 5;

    private static Blockchain instance = null;

    private Blockchain() {}

    public static Blockchain getInstance() {
        if (instance == null) {
            instance = new Blockchain();
            instance.addToBlockChain(instance.getGenesisBlock());
        }

        return instance;
    }

    public void addToBlockChain(Block block) {
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

    public Block getGenesisBlock() {
        Transaction trx = new Transaction(
            "",
            "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANr06tKOATfgwvQyGNqq2faXfJfIg9QfYci9MSQemFvUUTdlMCx6/mW9P04XeoPOj4K+mFK+IJGzBKFBIE4xy9MCAwEAAQ==",
            5000,
            1717779330000L,
            ""
        );

        Block genesis = new Block(0, "", 1234565454L);
        genesis.setNonce(1);
        genesis.addTransaction(trx);
        genesis.setHash(genesis.computeHash());

        return genesis;
    }
}
