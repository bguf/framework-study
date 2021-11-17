package com.bguf.demo02;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author gufb
 * @date 2021-09-29 20:41
 */
public class TestClient {
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("localhost", 8080));
            socketChannel.write(Charset.defaultCharset().encode("132146245412321343adfnaidsfnadsf"));
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
