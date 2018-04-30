package team.ecut.shenyou.client.main;


import team.ecut.shenyou.client.config.DeviceConfig;

/*
 * Author: Seven
 * Email : cpwu@foxmail.com
 * 2018-04-02 星期一 10:22
 */
public class DeviceStart {
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        try {
            new ShenyouDevice(DeviceConfig.address, DeviceConfig.port).run();
        } catch (InterruptedException e) {
            //DataRefresh.refreshLogs("Device stopped!!!");
            DeviceConfig.deviceChannel = null;
            //Controller.t = null;
            //ShowAlert.error(e.toString());
        } catch (Exception e) {
            //ShowAlert.error(e.getMessage());
            DeviceConfig.deviceChannel = null;
            //Controller.t = null;
            System.err.println("start ex");
            e.printStackTrace();
        }
    }


}
