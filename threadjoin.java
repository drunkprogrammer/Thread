import javax.swing.JFrame;


public class threadjoin extends JFrame {

	/**
	 * @param args
	 */
	private Thread threadA;
	private Thread threadB;
	private int value=10;
	private int value2=100;
	public threadjoin()
	{
		
	    threadA=new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
					    threadA.sleep(100);
						threadB.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.print(" "+value);
					value--;
					if(value==0)
						return;
				}
				
			}
		});
	    threadA.start();
	    threadB=new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						threadB.sleep(100);	
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				 System.out.print(" "+value2);
					     value2++;
					     if(value2>200)
						 return;
				}
			}
		});	
	   
	   threadB.start();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new threadjoin();
	}

}
