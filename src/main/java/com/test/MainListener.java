package com.test;

import com.test.blockchain.Transaction;
import com.test.node.Node;

public interface MainListener {
    public void onNodeConnected(Node node);

    public void onTransactionReceived(Transaction transaction);
}
