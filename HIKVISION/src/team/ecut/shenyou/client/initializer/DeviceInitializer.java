package team.ecut.shenyou.client.initializer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import team.ecut.shenyou.client.handler.DeviceHandler;

/*
 * Author: Seven
 * Email : cpwu@foxmail.com
 * 2018-04-02 星期一 10:18
 */
public class DeviceInitializer  extends ChannelInitializer<SocketChannel> {

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //pipeline.addLast("dd", new lineBasedFrameDecoder());
        ByteBuf delimiter = Unpooled.copiedBuffer(">".getBytes());
        pipeline.addLast("decode", new StringDecoder());
        pipeline.addLast("delimiter", new DelimiterBasedFrameDecoder(1024,delimiter));
        pipeline.addLast("handler", new DeviceHandler());
    }
}