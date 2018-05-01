package team.ecut.shenyou.client.test;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import team.ecut.shenyou.client.camera.JDialogDeviceStart;
import team.ecut.shenyou.client.camera.JDialogReadIP;
import team.ecut.shenyou.client.camera.JDialogSaveVideoPath;
import team.ecut.shenyou.client.protocol.CameraProtocol;

public class Main {

	public static void main(String[] args)
	{
		//JDialogDeviceStart camera01=new JDialogDeviceStart();
		//camera01.login("192.168.0.1");
//		CameraProtocol camera1=new CameraProtocol();
//		int i=camera1.judgeProtocol("<SERVER,CAMERA,RECORD#33000001_20180430153912#1>");
//		camera1.receiveProtocol("<SERVER,CAMERA,RECORD#33000001_20180430153912#1>");
//		System.out.println(i);
//		JDialogReadIP getFirstLevel=new JDialogReadIP();
//		List<String> ipsList=new ArrayList<String>();
//		ipsList=getFirstLevel.readIp(2);
//		for (String ip:ipsList) {
//			System.out.println(ip);
//		}
		JDialogSaveVideoPath saveVideoPath=new JDialogSaveVideoPath();
		try {
			saveVideoPath.saveLevelVedio(2,"20180430153912","330000","01");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
