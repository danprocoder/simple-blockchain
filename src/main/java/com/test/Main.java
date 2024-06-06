package com.test;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.test.blockchain.Block;
import com.test.blockchain.Blockchain;
import com.test.blockchain.MineListener;
import com.test.blockchain.Miner;
import com.test.blockchain.Transaction;
import com.test.node.Node;
import com.test.node.NodeAddress;
import com.test.node.NodeFinder;
import com.test.node.NodeFinderListener;

public class Main implements MainListener, NodeFinderListener, MineListener {
    
    ArrayList<Node> connectedNodes = new ArrayList<Node>();

    ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    Blockchain blockchain = Blockchain.getInstance();

    public static void main(String[] args) {
        NodeFinder finder = NodeFinder.getInstance();
        finder.findNodes(new Main());
    }

    @Override()
    public void onNodeFound(NodeAddress address) {
        Node node = new Node(address, this);
        node.start();
    }

    @Override()
    public void onNodeConnected(Node node) {
        this.connectedNodes.add(node);
    }

    @Override()
    public void onTransactionReceived(Transaction transaction) {
        System.out.println("Transaction received. Starting Mine: " + transaction.toString());
        Miner miner = Miner.getInstance();
        miner.generateBlock(transaction, this);
    }

    @Override()
    public void onBlockMined(Block block) {
        System.out.println("Block mined. Sending to connected nodes.");
        
        // Broadcast mine to all connected nodes
        for (Node node: this.connectedNodes) {
            try {
                JsonObject payload = new JsonObject();
                payload.addProperty("action", "block");

                JsonObject data = new JsonObject();
                data.addProperty("previousHash", block.getPreviousHash());
                data.addProperty("hash", block.getHash());
                data.addProperty("nonce", block.getNonce());
                data.addProperty("timestamp", block.getTimestamp());
                data.addProperty("transactions", block.getTransactions().toString());

                payload.add("data", data);

                node.sendMessage(new Gson().toJson(payload));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
