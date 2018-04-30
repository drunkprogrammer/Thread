package team.ecut.shenyou.client.main;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import team.ecut.shenyou.client.config.DeviceConfig;
import team.ecut.shenyou.client.handler.DeviceHandler;

import java.net.InetSocketAddress;

/*
 * Author: Seven
 * Email : cpwu@foxmail.com
 * 2018-04-02 星期一 10:20
 */
public class ShenyouDevice {
    private final String host;
    private final int port;

    public ShenyouDevice(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception {
        System.out.println("Device starting...");
        //DataRefresh.refreshLogs("Connecting...");
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new DeviceHandler());
            System.out.println(1);
            ChannelFuture future= bootstrap.connect().sync();
            System.out.println(2);
            DeviceConfig.deviceChannel = future.channel();
            //ChannelFuture future = bootstrap.sync();
            System.out.println(3);
            //started!
            DeviceConfig.deviceChannel.closeFuture().sync();//block here

            System.out.println(4);
        } finally {
            group.shutdownGracefully();
            System.out.println(5);
        }
    }
}
