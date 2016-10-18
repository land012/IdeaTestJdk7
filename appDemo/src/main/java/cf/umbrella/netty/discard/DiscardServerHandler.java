package cf.umbrella.netty.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by xudazhou on 2016/10/12.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        try {
            // 输入中文则会显示出错
//            while (in.isReadable()) {
//                System.out.print((char)in.readByte());
//                System.out.flush();
//            }

            // 另一种写法
//            System.out.print(in.toString(CharsetUtil.US_ASCII));

            byte[] buf = new byte[1024];
//            while (in.isReadable()) {
//
//            }
            in.readBytes(buf);
            System.out.println(buf.length);
        } finally {
            ReferenceCountUtil.release(msg);
        }

        // 将收到的信息写回客户端
//        ctx.write(msg);
//        ctx.flush();
    }
}
