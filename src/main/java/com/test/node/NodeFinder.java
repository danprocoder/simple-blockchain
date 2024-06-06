package com.test.node;

public class NodeFinder {
    private static NodeFinder instance;

    private NodeFinderListener listener;

    public static NodeFinder getInstance() {
        if (instance == null) {
            instance = new NodeFinder();
        }

        return instance;
    }

    public void findNodes(NodeFinderListener listener) {
        this.listener = listener;

        this.listener.onNodeFound(new NodeAddress("localhost", 12345));
    }
}
