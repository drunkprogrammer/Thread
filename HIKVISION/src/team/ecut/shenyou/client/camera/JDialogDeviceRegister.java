package team.ecut.shenyou.client.camera;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import com.sun.jna.NativeLong;
import com.sun.jna.ptr.NativeLongByReference;

/*
 *  @author:drunkprogrammer
 * https://github.com/drunkprogrammer
 *
 *  类：JDialogDeviceRegister
 *  类描述：设备登录
 * */

public class JDialogDeviceRegister {
	static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
	static HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo;// 设备信息
	static HCNetSDK.NET_DVR_CLIENTINFO m_strClientInfo;// 用户参数
	private static NativeLong lUserID;// 设备ID
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

	//设备注册
	public static void register(String ip) {
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

	public static NativeLong getlUserID() {
		return lUserID;
	}

	public static void setlUserID(NativeLong lUserID) {
		JDialogDeviceRegister.lUserID = lUserID;
	}
}
