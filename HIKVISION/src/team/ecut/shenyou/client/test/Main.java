package team.ecut.shenyou.client.test;



import team.ecut.shenyou.client.camera.JDialogDeviceStart;
import team.ecut.shenyou.client.protocol.CameraProtocol;

public class Main {

	public static void main(String[] args)
	{
		//JDialogDeviceStart camera01=new JDialogDeviceStart();
		//camera01.login("192.168.0.1");
		CameraProtocol camera1=new CameraProtocol();
		int i=camera1.judgeProtocol("<SERVER,CAMERA,RECORD#ID#N>");
		System.out.println(i);
	}
}
