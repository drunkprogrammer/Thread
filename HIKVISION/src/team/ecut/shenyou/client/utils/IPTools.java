package team.ecut.shenyou.client.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/*
 * Author: Seven
 * Email : cpwu@foxmail.com
 * 2018-04-04 星期三 09:12
 */
public class IPTools {
    public static String getLocolIP() {
        try {
            Enumeration<NetworkInterface> nifs = NetworkInterface.getNetworkInterfaces();
            while (nifs.hasMoreElements()) {
                NetworkInterface nif = nifs.nextElement();

                if (nif.getName().startsWith("wlan")) {
                    Enumeration<InetAddress> addresses = nif.getInetAddresses();

                    while (addresses.hasMoreElements()) {

                        InetAddress addr = addresses.nextElement();
                        if (addr.getAddress().length == 4) { // 速度快于 instanceof
                            //System.err.println(addr.getAddress());
                            //System.err.println(addr);
                            String ip = addr.toString();
                            return ip.substring(1,ip.length());
                        }
                        //System.out.println(addr);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "127.0.0.1";
   }
}