package team.ecut.shenyou.client.config;


import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.GenericFutureListener;
import team.ecut.shenyou.client.protocol.GameProtocol;
import team.ecut.shenyou.client.utils.StringToByteBuf;

/*
 * Author: Seven
 * Email : cpwu@foxmail.com
 * 2018-04-02 星期一 10:23
 */
public class DeviceConfig {
    public static String address = "192.168.1.101";
    public static int port = 8699;
    public static Channel deviceChannel = null;

    public static void send(GameProtocol protocol) {
        if (deviceChannel != null) {
//            if (FXConfig.devID.get().equals("未选择")) {
//                ShowAlert.warning("请先选择设备并注册！");
//                return;
//            }
            //DataRefresh.refreshLogs(TimeTools.nowms() + "\tSend: \t---->\t" + protocol);
            //System.out.println(protocol);
            ByteBuf bf = StringToByteBuf.getBuf(protocol.toString());
            ChannelFuture f =  deviceChannel.writeAndFlush(bf);
            f.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if(channelFuture.isSuccess()){
                        System.out.println("Send success:\t" + protocol);
                    }else{
                        System.err.println("Send fail:\t" + protocol);
                    }
                }
            });
        } else {
            //ShowAlert.error("Device is disconnect!");
        }
    }
}
