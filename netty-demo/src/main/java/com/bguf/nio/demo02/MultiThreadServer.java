package com.bguf.demo02;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static com.bguf.utils.ByteBufferUtil.debugAll;

/**
 * @author gufb
 * @date 2021-09-24 09:41
 */
@Slf4j
public class MultiThreadServer {
    public static void main(String[] args) throws IOException {
        Thread.currentThread().setName("boss");
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        Selector boss = Selector.open();
        SelectionKey bossKey = ssc.register(boss, 0, null);
        bossKey.interestOps(SelectionKey.OP_ACCEPT);
        ssc.bind(new InetSocketAddress(8080));
        // 1. 创建固定数量的worker并初始化
        // 创建的线程数和cpu核心数相同最好，
        // 建议手动设置，在jdk10之前，如果在docker下运行，获取到的核心数是物理机的核心数，
        // 而不是给docker分配的核心数
        // 这样获取不好，获取到的其实是线程数，不是核心数，比如4核8线程，最合适的是4个线程，而不是8个线程
        Worker[] workerArr = new Worker[Runtime.getRuntime().availableProcessors()];
        for (int i = 0; i < workerArr.length; i++) {
            workerArr[i] = new Worker("selector-" + i);
        }

        AtomicInteger index = new AtomicInteger();
        while (true) {
            boss.select();
            Iterator<SelectionKey> iterator = boss.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    log.info("connected...{}", sc.getRemoteAddress());
                    // 2. 关联到worker的selector
                    log.info("before register...{}", sc.getRemoteAddress());
                    // round robin实现线程轮询
                    workerArr[index.getAndIncrement() % workerArr.length].register(sc);
                    log.info("after register...{}", sc.getRemoteAddress());
                }
            }
        }
    }

    static class Worker implements Runnable {
        private Thread thread;
        private Selector selector;
        private String name;
        // 还未初始化
        private volatile boolean start = false;
        // 两个线程之间传递数据，使用队列作为数据的通道
        private ConcurrentLinkedQueue<Runnable> queue = new ConcurrentLinkedQueue<>();

        public Worker(String name) {
            this.name = name;
        }

        /**
         * 初始化线程和selector
         */
        public void register(SocketChannel sc) throws IOException {
            if (!start) {
                thread = new Thread(this, this.name);
                thread.start();
                selector = Selector.open();
                start = true;
            }
            // 向队列添加了任务，但这个任务并没有立刻执行
            /**
             * 为什么要这样做：
             * 将sc.register和worker.register放到一个线程中执行，
             * 可以保证sc.register在worker.register前执行
             * sc.register之后，才能允许worker.register中的新线程读取数据
             * 如果worker.register在前面执行，会在read上阻塞，此时sc.register也执行不了
             */
            queue.add(() -> {
                try {
                    sc.register(selector, SelectionKey.OP_READ, null);
                } catch (ClosedChannelException e) {
                    e.printStackTrace();
                }
            });
            // 唤醒select方法
            selector.wakeup();
        }

        @Override
        public void run() {
            while (true) {
                try {
                    selector.select();
                    Runnable task = queue.poll();
                    if (null != task) {
                        task.run();
                    }
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isReadable()) {
                            ByteBuffer buffer = ByteBuffer.allocate(16);
                            SocketChannel channel = (SocketChannel) key.channel();
                            log.debug("read...{}", channel.getRemoteAddress());
                            channel.read(buffer);
                            buffer.flip();
                            debugAll(buffer);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
