package team.ecut.shenyou.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import team.ecut.shenyou.client.config.DeviceConfig;
import team.ecut.shenyou.client.protocol.CameraProtocol;
import team.ecut.shenyou.client.utils.StringToByteBuf;


/*
 * Author: Seven
 * Email : cpwu@foxmail.com
 * 2018-04-02 星期一 10:16
 */
public class DeviceHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client complete");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client active! " + ctx.channel().remoteAddress());
        //DataRefresh.refreshLogs("Device connect success!\tHost: " + ctx.channel().remoteAddress());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client read: " + StringToByteBuf.getString((ByteBuf)msg));
        CameraProtocol cameraProtocol=new CameraProtocol();
        cameraProtocol.receiveProtocol(StringToByteBuf.getString((ByteBuf)msg));
        //DataRefresh.refreshLogs(TimeTools.nowms() + "\tReceive: \t<----\t" + StringToByteBuf.getString((ByteBuf)msg));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        System.out.println("client read0: " + s);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("client ex...................");
        //ShowAlert.error("client ex: " + cause.toString());
        ctx.disconnect();
        ctx.close();
        DeviceConfig.deviceChannel = null;
        //Controller.t = null;
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //ShowAlert.error("Server disconnected!");
        DeviceConfig.deviceChannel = null;
        //Controller.t = null;
        ctx.disconnect();
        ctx.close();
        System.err.println("client disconnected!");
        //DataRefresh.refreshLogs(TimeTools.nowms() + "\tServer disconnect!!!");

    }

}
