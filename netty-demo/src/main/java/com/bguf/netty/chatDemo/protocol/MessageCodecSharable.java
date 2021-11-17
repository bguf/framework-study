package com.bguf.netty.chatDemo.protocol;

import com.bguf.netty.chatDemo.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * @author gufb
 * @date 2021-10-11 19:27
 */
@Slf4j
public class MessageCodec extends ByteToMessageCodec<Message> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, ByteBuf byteBuf) throws Exception {
        // 1. 4字节的魔数
        byteBuf.writeBytes(new byte[]{1, 2, 3, 4});
        // 2. 1字节的版本号
        byteBuf.writeByte(1);
        // 3. 1字节的序列化方式：jdk--0， json--1
        byteBuf.writeByte(0);
        // 4. 1字节的指令类型
        byteBuf.writeByte(message.getMessageType());
        // 5. 4字节的请求序号
        byteBuf.writeInt(message.getSequenceId());

        // 6. 1字节的无意义字节，对齐填充用
        byteBuf.writeByte(0xff);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(message);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        // 7. 4字节的正文长度
        byteBuf.writeInt(byteArray.length);
        // 8. 消息正文
        byteBuf.writeBytes(byteArray);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        // 1. 4字节的魔数
        int magicNum = byteBuf.readInt();
        // 2. 1字节的版本号
        byte version = byteBuf.readByte();
        // 3. 1字节的序列化方式
        byte serializerType = byteBuf.readByte();
        // 4. 1字节的指令类型
        byte messageType = byteBuf.readByte();
        // 5. 4字节的请求序号
        int sequenceId = byteBuf.readInt();
        // 6. 无意义字节
        byteBuf.readByte();
        // 7. 4字节的正文长度
        int messageLength = byteBuf.readInt();
        // 8. 消息正文
        byte[] byteArr = new byte[messageLength];
        byteBuf.readBytes(byteArr, 0, messageLength);
        log.info("{}, {}, {}, {}, {}, {}", magicNum, version, serializerType, messageType, sequenceId, messageLength);
        if (serializerType == 0) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteArr));
            Message message = (Message) objectInputStream.readObject();
            list.add(message);
            log.info("{}", message);
        }
    }
}
