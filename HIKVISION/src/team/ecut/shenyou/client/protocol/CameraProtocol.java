package team.ecut.shenyou.client.protocol;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import com.sun.jna.NativeLong;

import team.ecut.shenyou.client.camera.JDialogDeviceRegister;
import team.ecut.shenyou.client.camera.JDialogDeviceStart;
import team.ecut.shenyou.client.camera.JDialogDeviceStop;
import team.ecut.shenyou.client.camera.JDialogReadIP;

/*
 * @author:drunkprogrammer
 * https://github.com/drunkprogrammer
 *
 * Camera protocol
 * <SERVER,CAMERA,RECORD#ID#N> 开始录像
 * <SERVER,CAMERA,FINISH#ID#N> 结束录像
 *
 * **/

public class CameraProtocol {

	private String source; // where the protocol come from
	private String target; // identify the target device
	private String params; // parameters
	static ArrayDeque<NativeLong> lUserIDCollection;


	@Override
	public String toString() {
		return "<" + source + "," + target + "," + params + ">";
	}

	// 判断接收到的协议是否符合<SERVER,CAMERA,RECORD#ID#N> <SERVER,CAMERA,FINISH#ID#N>的规范
	public int judgeProtocol(String rProtocol) {
		rProtocol = rProtocol.substring(1, rProtocol.length() - 1);
		String[] arguments = rProtocol.split(",");
		System.out.println(arguments[0]);
		if (arguments[0].equals("SERVER")) {
			if (arguments[1].equals("CAMERA")) {
				source = "SERVER";
				target = "CAMERA";
				params = arguments[2];
			} else {
				System.out.println("发错客户端了");
				return -1;
			}

		} else {
			System.out.println("不是从客户端发出的命令");
			return -1;
		}
		return 0;
	}

	// 接收到协议之后先判断是否符合规范，符合规范则开始解析
	public void receiveProtocol(String rprotocol) {
		int value = judgeProtocol(rprotocol);
		if (value == 0) {
			analysisProtocol();
			// System.out.println(command);
		}
	}

	// 解析协议参数,然后处理协议
	public void analysisProtocol() {
		String[] nodes = params.split("#");
		String action = nodes[0];// action:RECORD/FINISH 开始录像/结束录像
		String number = nodes[1];// number: POSTCODE_TEAMID 场地编号_团队编号
		int level = Integer.parseInt(nodes[2]);// LEVEL: 游戏关卡数
		String[] nodesPID = number.split("_");
		String postcodes = nodesPID[0];// 场地地区编号和分店编号
		String postcode = postcodes.substring(0, 6);// 场地地区编号
		System.out.println(postcode);
		String branchcode = postcodes.substring(6, 8);// 分店编号
		System.out.println(branchcode);
		String teamID = nodesPID[1];// 团队编号
		System.out.println(teamID);
		handleProtocol(action, teamID, level, postcode, branchcode);
	}

	public void handleProtocol(String action, String teamID, int level, String postcode, String branchcode) {
		if (action.equals("RECORD") || action.equals("FINISH")) {
			JDialogReadIP getLevelIP = new JDialogReadIP();
			List<String> ipsListIP = new ArrayList<String>();
			ipsListIP = getLevelIP.readIp(level);
			if (action.equals("RECORD")) {
				JDialogDeviceRegister deviceRegister[] = new JDialogDeviceRegister[ipsListIP.size()];
				JDialogDeviceStart deviceStart[] = new JDialogDeviceStart[ipsListIP.size()];
				int i = 0;
				for (String ip : ipsListIP) {
					deviceRegister[i].register(ip);
					lUserIDCollection.add(deviceRegister[i].getlUserID());
					deviceStart[i].start(ip, deviceRegister[i].getlUserID());
					try {
						deviceStart[i].startRecord(teamID, level, i+1, postcode, branchcode);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					i++;
				}

			}
			if (action.equals("FINISH")) {
				JDialogDeviceStop deviceStop[]=new JDialogDeviceStop[ipsListIP.size()];
				for (int j=0;j<ipsListIP.size();j++) {
					deviceStop[j].stopRecord(lUserIDCollection.pop());
				}
			}
		}
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

}
