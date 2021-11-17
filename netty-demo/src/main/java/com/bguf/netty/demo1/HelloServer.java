package com.bguf.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author gufb
 * @date 2021-10-08 15:12
 */
public class HelloServer {
    public static void main(String[] args) {
        // 1. 启动器：负责组装netty组件，启动服务器
        new ServerBootstrap()
                // 2. 添加组件：NioEventLoopGroup--包含event和selector
                .group(new NioEventLoopGroup())
                // 3. 选择服务器的ServerSocketChannel实现
                .channel(NioServerSocketChannel.class)
                // 4. boss负责处理连接，worker(child)负责处理读写，决定了worker(child)能执行什么操作(handler)
                .childHandler(
                        // 5. 和客户端连接后，进行数据读写的通道初始化：负责添加其他handler
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                                // 6. 添加handler
                                // StringDecoder将byteBuf转换为string
                                nioSocketChannel.pipeline().addLast(new StringDecoder());
                                // 自定义handler
                                nioSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                    @Override
                                    // 处理读事件
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        System.out.println(ctx.name());
                                        System.out.println(msg);
                                    }
                                });
                            }
                        })
                // 7. 绑定监听端口
                .bind(8080);
    }
}
