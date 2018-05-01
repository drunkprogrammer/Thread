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
 * 类描述 ：设备初始化注册,开始录像
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

	public static void start(String ip,NativeLong lUserID) {
		/* 实时预览 */
		IP=ip;
		int iChannelNum = 1;// 通道号
		m_strClientInfo = new HCNetSDK.NET_DVR_CLIENTINFO();
		m_strClientInfo.lChannel = new NativeLong(iChannelNum);
		lChannel = m_strClientInfo.lChannel;
		lRealPlayHandle = hCNetSDK.NET_DVR_RealPlay_V30(lUserID, m_strClientInfo, null, null, true);
		if (lRealPlayHandle.longValue() < 0) {
			System.out.println("预览失败:" + hCNetSDK.NET_DVR_GetLastError());/* 返回值为错误码 */
			boolean logout = hCNetSDK.NET_DVR_Logout(lUserID);
			hCNetSDK.NET_DVR_Logout(lUserID);
			return;
		}
	}


	public void startRecord(String teamID,int lev,int cNum,String postcode,String branchcode) throws IOException {
		System.out.println("开始录像");
		JDialogSaveVideoPath date=new JDialogSaveVideoPath();
		String filePath=date.saveLevelVedio(lev,teamID,postcode,branchcode);//生成路径
		File file = new File(filePath);
		file.mkdirs();
		String videoName=Integer.toString(cNum);
		String fileName =filePath+"\\"+videoName+".mp4";
		System.out.println("路径："+fileName);
		hCNetSDK.NET_DVR_SaveRealData(lRealPlayHandle, fileName);
	}


}
