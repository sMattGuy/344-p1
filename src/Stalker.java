import java.util.Vector;
import java.util.concurrent.TimeUnit;

class Stalker implements Runnable{
	//local variables
	private ID_Check line;
	/*
	private Kiosk kiosk;
	private ScanMachine scanMachine;
	*/
	public Stalker(ID_Check line){
		this.line = line;
		new Thread(this).start();
	}
	
	public void run(){
		try{
			while(this.line.getRemainingVoters() != 0){
				System.out.println(line);
				wasteTime(1000);
			}
		}
		catch(InterruptedException e){
			
		}
	}
	
	private void wasteTime(int time) throws InterruptedException{
		TimeUnit.MILLISECONDS.sleep(time);
	}
}