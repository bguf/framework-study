package com.bguf.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

/**
 * @author gufb
 * @date 2021-10-08 15:57
 */
public class EventLoopClient {
    public static void main(String[] args) throws InterruptedException {
        // 1. 添加启动器
        ChannelFuture channelFuture = new Bootstrap()
                // 2. 添加EventLoop
                .group(new NioEventLoopGroup())
                // 3. 选择客户端channel实现
                .channel(NioSocketChannel.class)
                // 4. 添加处理器
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new StringEncoder());
                    }
                })
                .connect(new InetSocketAddress("localhost", 8080));
       /* channelFuture.sync();
        Channel channel = channelFuture.channel();
        System.out.println(channel);
        System.out.println("ok");*/
       channelFuture.addListener(new ChannelFutureListener() {
           @Override
           public void operationComplete(ChannelFuture channelFuture) throws Exception {
               Channel channel = channelFuture.channel();
               System.out.println(channel);
           }
       });

    }
}
