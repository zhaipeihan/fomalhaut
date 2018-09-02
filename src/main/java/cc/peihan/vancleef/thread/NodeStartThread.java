package cc.peihan.vancleef.thread;

import cc.peihan.vancleef.p2p.Node;

public class NodeStartThread implements Runnable {

    private Node node;

    public NodeStartThread(Node node) {
        this.node = node;
    }

    @Override
    public void run() {
        node.start();
    }
}
