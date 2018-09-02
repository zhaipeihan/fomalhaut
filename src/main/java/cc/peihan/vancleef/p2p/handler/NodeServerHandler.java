package cc.peihan.vancleef.p2p.handler;

import cc.peihan.vancleef.model.Block;
import cc.peihan.vancleef.model.BlockChain;
import cc.peihan.vancleef.p2p.Action;
import cc.peihan.vancleef.p2p.RequestMessage;
import cc.peihan.vancleef.util.SerializeUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;


public class NodeServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private static final BlockChain blockChain = BlockChain.getInstance();
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        logger.info("收到");
        ByteBuf byteBuf = msg.copy().content();
        byte[] buf = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(buf);
        RequestMessage requestMessage = (RequestMessage) SerializeUtil.deSerialize(buf);

        if (requestMessage == null) {
            ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("ERROR", CharsetUtil.UTF_8), msg.sender()));
            return;
        }

        if (requestMessage.getAction() == Action.GET_ALL_BLOCK) {
            List<Block> blocks = blockChain.getAllBlocks();
            byte[] response = SerializeUtil.serialize(blocks);
            logger.info("GET_ALL_BLOCK responce : {}", Arrays.toString(response));
            ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(response), msg.sender()));
        } else if (requestMessage.getAction() == Action.PUSH_ALL_BLOCK) {
            List<Block> blocks = (List<Block>) requestMessage.getData();
            logger.info("PUSH_ALL_BLOCK request : {}", blocks);
            blockChain.consensus(blocks);
            ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(new byte[]{1}), msg.sender()));
        } else {
            ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("ERROR", CharsetUtil.UTF_8), msg.sender()));
        }
    }
}
