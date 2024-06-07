package com.test.node;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.test.MainListener;
import com.test.Wallet;
import com.test.blockchain.Transaction;

public class Node extends Thread {
    NodeAddress address;

    MainListener listener;

    Socket socket;

    public Node(NodeAddress address, MainListener listener) {
        this.address = address;
        this.listener = listener;
    }

    @Override
    public void run() {
        try {
            this.socket = new Socket(this.address.getHost(), this.address.getPort());
            System.out.println("Connected to node on port " + this.address.getPort());
            // Handshake
            if (this.initiateHandshake()) {
                this.listener.onNodeConnected(this);

                this.getMessages();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) throws IOException {
        DataOutputStream dos = new DataOutputStream(this.socket.getOutputStream());
        dos.write(message.getBytes());
        dos.flush();
    }

    private void getMessages() throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = this.socket.getInputStream().read(buffer)) != -1) {
            String json = new String(buffer, 0, bytesRead);

            Gson gson = new Gson();
            Message message = gson.fromJson(json, Message.class);
            LinkedTreeMap<String, Object> payload = message.getData();

            if (message.getAction().equals("transaction")) {
                this.listener.onTransactionReceived(
                    new Transaction(
                        (String) payload.get("from"),
                        (String) payload.get("to"),
                        (Double) payload.get("amount"),
                        ((Double) payload.get("timestamp")).longValue(),
                        (String) payload.get("signature")
                    )
                );
            }
        }
    }

    private boolean initiateHandshake() throws IOException {
        DataOutputStream dos = new DataOutputStream(this.socket.getOutputStream());
        String header = "MINER 1.0\r\nAddress: " + Wallet.getAddress() + "\r\n\r\n";
        dos.write(header.getBytes());
        dos.flush();

        return true;
    }

}
