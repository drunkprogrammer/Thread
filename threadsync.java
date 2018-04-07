
public class threadsync implements Runnable{

	/**
	 * @param args
	 */
	private int value=10;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		threadsync t1=new threadsync();
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
		while (true) {
			synchronized ("") {
				if(value>0)
				{
					try {
						
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					value--;
					System.out.println(" "+value);
				}
			}
			
			
		}
		
	}

}
