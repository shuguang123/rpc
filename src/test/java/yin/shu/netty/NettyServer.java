package yin.shu.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.util.ReferenceCountUtil;
import yin.shu.netty.decoder.UserDecoder;
import yin.shu.netty.decoder.UserEncoder;
import yin.shu.netty.serialize.User;


/**
 * @author
 * @create 2018-01-30 20:43
 **/
public class NettyServer {

    /**
     * 用于分配处理业务线程的线程组个数
     */
    protected static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors()*2; //默认

    /**
     *业务出现线程大小
     */
    protected static final int BIZTHREADSIZE = 4;

    private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
    private static final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);


    public void run() throws InterruptedException {

            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class) // (3)
                .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                        pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                        pipeline.addLast("decoder", new UserDecoder());
                        pipeline.addLast("encoder", new UserEncoder());
                        pipeline.addLast(new ServerHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

                 ChannelFuture f = b.bind("localhost",9873).sync();


                System.out.println("netty server start success...");

                f.channel().closeFuture().sync();

                 shutdown();
    }

    protected static void shutdown() {
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

    public static void main(String[] args) {
        try {
            new NettyServer().run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class ServerHandler extends SimpleChannelInboundHandler<Object> {

    /**
     * 请求的超时时间
     */
    private static final long TIMEOUT = 2*60*1000L;

    /**
     * cache的过期时间：60s
     */
    private static final long MILSECONDS = 1000*60;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        User user = (User)msg;

        System.out.println("服务端接收到User,名称："+ user.getUsername() +"  age: "+ user.getAge());

        ctx.channel().writeAndFlush(user);
    }

    /**
     * Close the connection when an exception is raised.
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) throws Exception {
        ctx.close();
    }
}



/**
 *
 * server端消息处理
 */
class DiscardServerHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)

        ByteBuf in = (ByteBuf) msg;
       try {
            while(in.isReadable()){
                System.out.println((char)in.readByte());
                System.out.flush();
            }

       }finally {
           ReferenceCountUtil.releaseLater(msg);
       }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}



class TimeServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(final ChannelHandlerContext ctx) { // (1)
        final ByteBuf time = ctx.alloc().buffer(4); // (2)
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

        final ChannelFuture f = ctx.writeAndFlush(time); // (3)
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                assert f == future;
                ctx.close();
            }
        }); // (4)
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
