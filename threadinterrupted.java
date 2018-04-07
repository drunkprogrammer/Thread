import sun.awt.SunHints.Value;


public class threadinterrupted implements Runnable{

	private boolean isContinue=false;
	private int value=10;
	private Thread t;
	public threadinterrupted()
	{
		t=new Thread(new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					/*if(isContinue)// if isContinue is true the thread interrupt			
						return;
					else {
						value--;
						System.out.print(value+" ");
						if(value<1)
							isContinue=true;
					}*/
					try {
						t.sleep(100);
					} catch (InterruptedException e) {
						// TODO: handle exception
						System.out.println("interrupte exception");
						e.printStackTrace();
						
					}
					
				}
			}
		});
		t.start();
		t.interrupt();
	}
	
	public void setContiue()
	{
		this.isContinue=true;
	}

	
	public static void main(String[] args)
	{
		new threadinterrupted();
		
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
