package cf.umbrella.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * Created by xudazhou on 2016/10/14.
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        try {
            long timestamp = buf.readUnsignedInt();
            System.out.println(new Date((timestamp-2208988800L)*1000));

//            System.out.println(new Date(timestamp*1000));
            ctx.close();
        } finally {
            buf.release();
        }
    }
}
