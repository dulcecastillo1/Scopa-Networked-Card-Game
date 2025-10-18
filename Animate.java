public class Animate implements Runnable {
	
	private ClientScreen sc;
	
	public Animate(ClientScreen sc){
		this.sc = sc;
	}
	
	public void run(){
		while( true ){
			
			try{
				Thread.sleep(10); //millisecond
			} catch(InterruptedException ex){
				Thread.currentThread().interrupt();
			}
			
			//redraw the screen
			sc.repaint();
		}
	}
}
