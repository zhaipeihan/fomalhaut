package cc.peihan.vancleef;

import cc.peihan.vancleef.cli.Cli;
import cc.peihan.vancleef.exception.base.ServiceException;
import cc.peihan.vancleef.p2p.Node;
import cc.peihan.vancleef.thread.NodeStartThread;

public class App {

    //main线程负责ui交互，NodeServer线程负责响应网络中其他结点的请求
    public static void main(String[] args) throws ServiceException {
        Cli cli = new Cli();
        //启动节点
        //new Thread(new NodeStartThread(Node.getInstance())).start();
        //主线程负责ui交互
        cli.ui();
    }


}
