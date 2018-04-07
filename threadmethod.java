import javax.swing.JFrame;


public class threadmethod extends JFrame{

	private Thread t;
	private int value=10;
	public threadmethod()
	{
		t=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						t.sleep(2000);
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
		t.start();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new threadmethod();
	}

}
