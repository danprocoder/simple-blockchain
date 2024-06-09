package com.test.miner;

import com.test.blockchain.Block;

public interface MineListener {
    public void onBlockMined(Block block);
}
