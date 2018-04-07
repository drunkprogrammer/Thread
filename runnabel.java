import javax.swing.JFrame;

	public class runnabel extends JFrame{

		private static final long serialVersionUID = 1L;
		private int value=10;
		private Thread t;
		public runnabel(){
			t=new Thread(new Runnable() {
				
				@Override
				public void run() {
					while (true) {
						System.out.print(" "+value);
						value--;
						if(value==0)
							return;
					}
				}
			});
			t.start();

		}
		
	
	public static void main(String[] args)
	{
		new runnabel();
	}
}
