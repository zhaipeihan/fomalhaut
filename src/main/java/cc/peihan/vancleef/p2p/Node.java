package cc.peihan.vancleef.p2p;

import cc.peihan.vancleef.config.Config;
import cc.peihan.vancleef.model.Block;
import cc.peihan.vancleef.p2p.handler.NodeClientHandler;
import cc.peihan.vancleef.p2p.handler.NodeServerHandler;
import cc.peihan.vancleef.p2p.handler.PushToOtherNodeHandler;
import cc.peihan.vancleef.util.MagicUtil;
import cc.peihan.vancleef.util.SerializeUtil;
import cc.peihan.vancleef.util.YamlUtil;
import cc.peihan.vancleef.exception.OperateFailedException;
import cc.peihan.vancleef.exception.base.SystemException;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.internal.SocketUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Set;

public class Node {

    private static final Logger logger = LogManager.getLogger();

    private static volatile Node INSTANCE;

    private int port;

    private Set<NeighborNode> neighborNodes;


    public static Node getInstance() throws OperateFailedException {
        if (INSTANCE == null) {
            synchronized (Node.class) {
                if (INSTANCE == null) {
                    Config config = YamlUtil.getConfig();
                    INSTANCE = new Node(config.getPort(), MagicUtil.getNeighborNodes(config.getIpPorts()));
                }
            }

        }
        return INSTANCE;
    }

    private Node(int port, Set<NeighborNode> neighborNodes) {
        this.port = port;
        this.neighborNodes = neighborNodes;
    }

    //启动节点，监听在port上，以接受其他节点的访问
    public void start() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new NodeServerHandler());
            b.bind(port).sync().channel().closeFuture().await();
        } catch (Exception e) {
            throw new SystemException("节点启动失败");
        } finally {
            group.shutdownGracefully();
        }
    }


    //向其他节点请求数据
    public void requestAllBlock() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new NodeClientHandler());

            Channel ch = b.bind(0).sync().channel();

            RequestMessage requestMessage = new RequestMessage(Action.GET_ALL_BLOCK, null);

            for (NeighborNode neighborNode : this.neighborNodes) {
                ch.writeAndFlush(new DatagramPacket(
                        Unpooled.copiedBuffer(SerializeUtil.serialize(requestMessage)),
                        SocketUtils.socketAddress(neighborNode.getIp(), neighborNode.getPort()))).sync();

                if (!ch.closeFuture().await(5000)) {
                    logger.error("ip:{},port:{},time out!");
                }
            }
        } catch (Exception e) {
            throw new SystemException("请求其他节点数据error");

        } finally {
            group.shutdownGracefully();
        }
    }

    public void pushToOtherNode(List<Block> blocks) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new PushToOtherNodeHandler());

            Channel ch = b.bind(0).sync().channel();

            RequestMessage requestMessage = new RequestMessage(Action.PUSH_ALL_BLOCK, blocks);

            for (NeighborNode neighborNode : this.neighborNodes) {
                ch.writeAndFlush(new DatagramPacket(
                        Unpooled.copiedBuffer(SerializeUtil.serialize(requestMessage)),
                        SocketUtils.socketAddress(neighborNode.getIp(), neighborNode.getPort()))).sync();

                if (!ch.closeFuture().await(500000)) {
                    logger.error("ip:{},port:{},time out!");
                }
            }
        } catch (Exception e) {
            throw new SystemException("请求其他节点数据error");

        } finally {
            group.shutdownGracefully();
        }
    }
}
