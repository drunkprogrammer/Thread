import org.omg.CORBA.PRIVATE_MEMBER;


public class main extends Thread{

	/**
	 * @param args
	 */
	private int value=10;
	public void run()
	{
		while (true) {
			System.out.print(" "+value);
			value--;
			if(value==0)
				return;
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new main().start();
	}

}
