
public class threadpriority implements Runnable{

	private Thread aThread;
	private Thread bThread;
	private Thread cThread;
	private Thread dThread;
	public threadpriority()
	{
		aThread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("a");
			}
		});
	
        bThread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("b");
			}
		});
		
        cThread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("c");
			}
		});
		
        dThread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("d");
			}
		});
		
		
		aThread.setPriority(Thread.MAX_PRIORITY);//the max priority
		aThread.start();
		bThread.setPriority(4);//1~10 priority
		bThread.start();
		cThread.setPriority(5);
		cThread.start();
		dThread.setPriority(Thread.MIN_PRIORITY);//the min priority
		dThread.start();
		
	}
	public void run() {
		// TODO Auto-generated method stub
		
	}
    
	public static void main(String[] args)
	{
	  new threadpriority();
	}
}
