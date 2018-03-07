package yin.shu.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import yin.shu.netty.decoder.UserDecoder;
import yin.shu.netty.decoder.UserEncoder;
import yin.shu.netty.serialize.User;

import java.util.Date;

/**
 * Client
 *
 * @author
 * @create 2018-01-30 21:09
 **/
public class NettyClient {

    public static String HOST = "localhost";
    public static int PORT = 9873;

    public static Bootstrap bootstrap = getBootstrap();
    public static Channel channel = getChannel(HOST,PORT);


    public static Bootstrap getBootstrap(){

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap b =new Bootstrap();
        b.group(workerGroup);
        b.channel(NioSocketChannel.class);
        b.option(ChannelOption.SO_KEEPALIVE,true);
        b.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                pipeline.addLast("decoder", new UserDecoder());
                pipeline.addLast("encoder", new UserEncoder());
                pipeline.addLast("handler", new ClientHandler());
            }
        });

        return b;
    }

    public static final Channel getChannel(String host,int port){
        Channel channel = null;
        try {
            channel = bootstrap.connect(host, port).sync().channel();
        } catch (Exception e) {
            return null;
        }
        return channel;
    }


    public static void sendUser(User user) throws Exception {
        System.out.println(channel);
        if(channel!=null){
            channel.writeAndFlush(user).sync();
        }else{
            System.out.print("消息发送失败,连接尚未建立!");
        }
    }

    public static void main(String[] args) {
        try {
            NettyClient.sendUser(new User("kenvin",24));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class ClientHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.print("client接收到服务器返回的消息:"+(User)msg);
    }

}




class TimeClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf m = (ByteBuf) msg; // (1)
        try {
            long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.close();
        } finally {
            m.release();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}