package yin.shu.netty.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * User解码
 *
 * @author
 * @create 2018-01-30 22:03
 **/
public class UserDecoder extends ByteToMessageDecoder {



    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
       byte[] bytes = new byte[byteBuf.readableBytes()];
       byteBuf.readBytes(bytes);

       Object obj =  ByteObjConveter.byteToObject(bytes);
       list.add(obj);
    }
}
