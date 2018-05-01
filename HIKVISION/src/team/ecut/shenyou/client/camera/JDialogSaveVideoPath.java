package team.ecut.shenyou.client.camera;

/*
 * @author drunkprogrammer
 * https://github.com/drunkprogrammer
 *
 * 类名：JDialogSaveVideoPath
 *
 * 类描述：视频录像的保存路径
 * */



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class JDialogSaveVideoPath {

	private static String root = "D:\\shenyou\\video";  //根目录
	private String teamId="01";
	private static String dateString;
	private static String rootDir;
	private static String postcode="330000"; //设置在数据库的场地邮编
	private static String branch="01";	//分店编号 取值01-99
	private static String fileName="D:\\video\\test.mp4";//保存的录像文件名


	public JDialogSaveVideoPath() {
		super();
	}


	public JDialogSaveVideoPath(String teamId,String postcode,String branch) {
		super();
		this.teamId = teamId;
		this.postcode=postcode;
		this.branch=branch;
	}

	//PATH: d:/shenyou/video/33000001/20180430153912/1/1.mp4
	public  String saveLevelVedio(int levelInt,String teamID,String postcode,String branch) throws IOException{
		this.teamId = teamId;
		rootDir=root+"\\"+postcode+branch;
		System.out.println("JDialogSaveVideoPath  teamId: "+teamId);
		int level =levelInt;
		// 团队目录
		String groupDir =rootDir+"\\"+teamID+"\\";
		// 创建团队目录
		dateString=groupDir+level;
		fileName=dateString;
		System.out.println("文件保存路径："+fileName);
		return fileName;
	}
}
