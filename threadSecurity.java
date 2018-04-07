
public class threadSecurity implements Runnable{

	/**
	 * @param args
	 */
	private static int value=10;
	private Thread thread;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		threadSecurity t1=new threadSecurity();
		Thread t2=new Thread(t1);
		Thread t3=new Thread(t1);
		Thread t4=new Thread(t1);
		t2.start();
		t3.start();
		t4.start();
	}
   
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			if(value>0)
			{
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				value--;
				System.out.print(" "+value);
				
			}
		
		}
	}

}
