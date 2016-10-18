package cf.umbrella.netty.time;

import io.netty.buffer.ByteBuf;
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
//        System.out.println(1);
        ByteBuf time = ctx.alloc().buffer(4);
        /**
         * 为什么要 + 2208988800L，不加也可以啊
         * 为了保证发送的是4字节的信息么？
         */
        int timestamp = (int)(System.currentTimeMillis()/1000L + 2208988800L);
        System.out.println(timestamp); // -609550391
        time.writeInt(timestamp);

//        time.writeInt((int)(System.currentTimeMillis()/1000L));

//        time.writeChar(65); // A
        ChannelFuture f = ctx.writeAndFlush(time);
        f.addListener(ChannelFutureListener.CLOSE);
    }
}
