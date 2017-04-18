package handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by zhugongyi on 2017/4/18.
 */
public class TestClientInBoundHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("TestClientInBoundHandler channelRead");
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        byteBuf.release();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("HelloClientIntHandler.channelActive");
        String msg = "Are you ok?";
        ByteBuf encoded = ctx.alloc().buffer(4 * msg.length());
        encoded.writeBytes(msg.getBytes());
        ctx.write(encoded);
        ctx.flush();
    }
}
