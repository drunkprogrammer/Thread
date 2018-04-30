package team.ecut.shenyou.client.camera;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

import javax.xml.stream.events.StartDocument;

import com.sun.jna.NativeLong;
import com.sun.jna.examples.win32.W32API.LONG;
import com.sun.jna.ptr.NativeLongByReference;


/*
 * @author drunkprogrammer
 *
 * 类 ：JDialogDeviceStart
 *
 * 类描述 ：设备初始化注册,开始录像，结束录像
 *
 */

public class JDialogDeviceStart {

	static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
	static HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo;// 设备信息
	static HCNetSDK.NET_DVR_CLIENTINFO m_strClientInfo;// 用户参数
	static NativeLong lUserID;// 设备ID
	static long userID;// 设备注册值
	static NativeLong lRecordType;// 录像类型
	static NativeLong lChannel;
	static HCNetSDK.NET_DVR_JPEGPARA m_strJpegpara;// 图像信息
	static String sPicFileName;// 图像保存路径
	static String IP;//设备IP地址
	static NativeLong lRealPlayHandle;
	int level=1;//关卡编号
	NativeLong lPreviewHandle;// 预览句柄
	NativeLongByReference m_lPort;// 回调预览时播放库端口指针


	public static void login(String ip) {
		IP=ip;
		String ipDevice = IP;
		Properties user=new Properties();
		try {
			user.load(new FileInputStream("D:\\shenyou\\config\\cameraLoginConfig.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Enumeration enumeration=user.propertyNames();
		String userName =user.getProperty("ADMINSTRATOR");
		String password =user.getProperty("PASSWORD");
		short dvrPort = Short.parseShort(user.getProperty("PORT"));
//		String userName = new String("admin");
//		String ipDevice = IP;
//		String password = new String("1234qwer");
//		short dvrPort = new Short((short) 8000);
//		配置文件在：D:\\shenyou\\config\\cameraLoginConfig.txt



		/* 设备信息 */
		m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
		//System.out.println(m_strDeviceInfo);

		/* 设备初始化 */
		boolean initSuc = hCNetSDK.NET_DVR_Init();
		if (initSuc == true) {
			System.out.println("摄像头初始化成功");

		} else {
			System.out.println("摄像头初始化失败");
			if (hCNetSDK.NET_DVR_GetLastError() == 41) {
				System.out.println("SDK资源分配错误");
			}
			if (hCNetSDK.NET_DVR_GetLastError() == 53) {
				System.out.println("获得本地PC的IP地址或物理地址失败");
			}
		}

		/* 设备注册 */
		lUserID = hCNetSDK.NET_DVR_Login_V30(ipDevice, dvrPort, userName, password, m_strDeviceInfo);

		userID = lUserID.longValue();

		/* 注册失败 */
		if (userID < 0) {
			System.out.println("注册失败:" + hCNetSDK.NET_DVR_GetLastError());/* 返回值为错误码 */
			return;
		}
		System.out.println("注册成功");

	}

	public static void start(String ip,int num) {

		/* 实时预览 */
		login(ip);
		System.out.println("开始预览");
		int iChannelNum = num;// 通道号
		m_strClientInfo = new HCNetSDK.NET_DVR_CLIENTINFO();
		m_strClientInfo.lChannel = new NativeLong(iChannelNum);
		lChannel = m_strClientInfo.lChannel;
		lRealPlayHandle = hCNetSDK.NET_DVR_RealPlay_V30(lUserID, m_strClientInfo, null, null, true);
		if (lRealPlayHandle.longValue() < 0) {
			System.out.println("预览失败");
			System.out.println("预览失败:" + hCNetSDK.NET_DVR_GetLastError());/* 返回值为错误码 */
			boolean logout = hCNetSDK.NET_DVR_Logout(lUserID);
			hCNetSDK.NET_DVR_Logout(lUserID);
			return;
		}
		// lPreviewHandle = hCNetSDK.NET_DVR_RealPlay_V40(lUserID,
		// m_strClientInfo);

		/* 实时保存数据 */
		// hCNetSDK.NET_DVR_SaveRealData(lRealHandle, sFileName);
	}

	public static void stop() {
		System.out.println("停止预览");
		hCNetSDK.NET_DVR_StopRealPlay(lRealPlayHandle);
		hCNetSDK.NET_DVR_Cleanup();
	}

	public void startRecord(String ip,int num,int lev) throws IOException {
		start(ip,num);
		System.out.println("开始录像");
		JDialogSaveVideoPath date=new JDialogSaveVideoPath();
		String filePath=date.saveLevelVedio(lev);
		File file = new File(filePath);
		file.mkdirs();
		String videoName=Integer.toString(lev)+Integer.toString(num);
		String fileName =filePath+"\\"+videoName+".mp4";
		System.out.println("路径："+fileName);
		hCNetSDK.NET_DVR_SaveRealData(lRealPlayHandle, fileName);
	}

	public void stopRecord() {
		System.out.println("结束录像");
		hCNetSDK.NET_DVR_StopSaveRealData(lUserID);
		hCNetSDK.NET_DVR_Cleanup();
	}
}
