package team.ecut.shenyou.client.camera;

/*
 *@author drunkprogrammer
 *
 * 类 ：JDialogReadIP
 *
 * 类描述 ：从配置文件中读取摄像头IP地址
 *
 * */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



public class JDialogReadIP {
	static String filePath = "D:\\shenyou\\config\\cameraIPConfig.txt";
	static ArrayList<List<String>> ipAddress=new ArrayList<>();
	static List<String> passIPAddress=new ArrayList<>();

	public static List<String> readIp(int level) {
		try {
			File file = new File(filePath);
			if (file.isFile() && file.exists()) {
				InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
				BufferedReader br = new BufferedReader(isr);
				String lineTxt = null;
				int pass = 1;//关卡编号
				while ((lineTxt = br.readLine()) != null) {
					int index=lineTxt.indexOf("址");
					lineTxt = lineTxt.substring(index+2, lineTxt.length() - 1);
					String[] passAddresStrings = lineTxt.split("\\]\\[");
					List<String> list = new ArrayList<>();
					for(String s: passAddresStrings)
					{
						list.add(s);
					}
					ipAddress.add(list);
					pass++;
				}
				br.close();
			} else {
				System.out.println("文件不存在!");
			}
		} catch (Exception e) {
			System.out.println("文件读取错误!");
		}
		passIPAddress=ipAddress.get(level-1);
		return passIPAddress;
	}

}
