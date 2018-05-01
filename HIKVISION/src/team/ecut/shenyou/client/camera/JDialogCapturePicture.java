package team.ecut.shenyou.client.camera;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.jna.NativeLong;

public class JDialogCapturePicture {

	static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
	static HCNetSDK.NET_DVR_JPEGPARA m_strJpegpara;// 图像信息
	static String sPicFileName;// 图像保存路径
	static NativeLong lChannel;
	static NativeLong lUserID;// 设备ID

	public void capturePicture(NativeLong lUserID,NativeLong lChannel,int levelInt,String teamID,String postcode,String branch) {
		System.out.println("开始截图");
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String dateString = format.format(date);
		JDialogSaveVideoPath datePicture=new JDialogSaveVideoPath();
		try {
			String filePath=datePicture.saveLevelVedio(levelInt,teamID, postcode, branch);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/* 捕获图像 */
		m_strJpegpara = new HCNetSDK.NET_DVR_JPEGPARA();
		m_strJpegpara.wPicQuality = 0;// 图像质量系数 0-最好 1-较好 2-一般
		m_strJpegpara.wPicSize = 9;// 图片尺寸9-HD1080P(1920*1080)
		sPicFileName = "D:\\"+dateString+".jpeg";
		boolean captureJPEG = hCNetSDK.NET_DVR_CaptureJPEGPicture(lUserID, lChannel, m_strJpegpara, sPicFileName);
		if (captureJPEG) {
			System.out.println("抓图成功");
		} else {
			System.out.println("抓图失败：" + hCNetSDK.NET_DVR_GetLastError());
		}

	}

}
