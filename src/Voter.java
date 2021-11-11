import java.util.Vector;
import java.util.concurrent.TimeUnit;

class Voter implements Runnable{
	//local variables
	private String name;
	private ID_Check line;
	/*
	private Kiosk kiosk;
	private ScanMachine scanMachine;
	*/
	public Voter(String name, ID_Check line){
		this.name = name;
		this.line = line;
		new Thread(this).start();
	}
	
	public void run(){
		try{
			line.enterLine(this.name);
			this.wasteTime(1000);
			System.out.println(line);
			line.exitLine(this.name);
			System.out.println("Goodbye " + this.name + "! See you next time.");
			System.out.println(line);
		}
		catch(InterruptedException e){
			System.out.println(e);
		}
	}
	
	private void wasteTime(int time) throws InterruptedException{
		TimeUnit.MILLISECONDS.sleep(time);
	}
}