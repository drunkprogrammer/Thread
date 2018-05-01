package team.ecut.shenyou.client.camera;

import com.sun.jna.NativeLong;
import com.sun.jna.ptr.NativeLongByReference;

public class JDialogDeviceStop {
	static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
	static HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo;// 设备信息
	static HCNetSDK.NET_DVR_CLIENTINFO m_strClientInfo;// 用户参数
	static NativeLong lUserID;// 设备ID
	static long userID;// 设备注册值
	static NativeLong lRecordType;// 录像类型
	static NativeLong lChannel;
	static HCNetSDK.NET_DVR_JPEGPARA m_strJpegpara;// 图像信息
	static String sPicFileName;// 图像保存路径
	static String IP;// 设备IP地址
	static NativeLong lRealPlayHandle;
	int level = 1;// 关卡编号
	NativeLong lPreviewHandle;// 预览句柄
	NativeLongByReference m_lPort;// 回调预览时播放库端口指针

	public void stopRecord(NativeLong lUserID) {
		System.out.println("结束录像");
		hCNetSDK.NET_DVR_StopSaveRealData(lUserID);
		hCNetSDK.NET_DVR_Cleanup();
	}

}
