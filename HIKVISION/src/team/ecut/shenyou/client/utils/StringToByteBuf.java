package team.ecut.shenyou.client.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/*
 * Author: Seven
 * Email: cpwu@foxmail.com
 * 2018-03-27 星期二 20:31
 */
public class StringToByteBuf {
    public static ByteBuf getBuf(String string){
        return Unpooled.copiedBuffer(string, CharsetUtil.UTF_8);
    }
    public static String getString(ByteBuf bf){
        String s = bf.toString(CharsetUtil.UTF_8);
        return s;
    }
}
