package yin.shu.netty.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import yin.shu.netty.serialize.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * User 编码
 *
 * @author
 * @create 2018-01-30 22:06
 **/
public class UserEncoder extends MessageToByteEncoder<User> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, User user, ByteBuf byteBuf) throws Exception {

        byte[] data = ByteObjConveter.objectToByte(user);

        byteBuf.writeBytes(data);

        channelHandlerContext.flush();
    }


}

