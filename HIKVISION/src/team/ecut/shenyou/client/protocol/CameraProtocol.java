package team.ecut.shenyou.client.protocol;

/*
 * Camera protocol
 * <SERVER,CAMERA,RECORD#ID#N> 开始录像
 * <SERVER,CAMERA,FINISH#ID#N> 结束录像
 *
 * **/

public class CameraProtocol {

    private String source;  //where the protocol come from
    private String target;  //identify the target device
    private String params;  //parameters


    @Override
    public String toString() {
        return "<" + source + "," + target + "," + params + ">";
    }

    public int judgeProtocol(String sendProtocol){
    	//sendProtocol=sendProtocol.substring(1,sendProtocol.length()-1);
    	String []arguments=sendProtocol.split(",");
    	String command;
    	System.out.println(arguments[0]);
    	if (arguments[0].equals("SERVER")) {
			if (arguments[1].equals("CAMERA")) {
				command=arguments[2];
			}
			else {
				System.out.println("发错客户端了");
				return -1;
			}

		}
    	else {
    		System.out.println("不是从客户端发出的命令");
			return -1;
		}
		return 0;
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
