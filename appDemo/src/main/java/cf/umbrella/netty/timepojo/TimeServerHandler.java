package cf.umbrella.netty.timepojo;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by xudazhou on 2016/10/12.
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UnixTime ut = new UnixTime();
        System.out.println(ut);
        ChannelFuture f = ctx.writeAndFlush(ut);
        f.addListener(ChannelFutureListener.CLOSE);
    }
}
