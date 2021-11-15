import java.util.Vector;
import java.util.concurrent.TimeUnit;

class Stalker implements Runnable{
	//local variables
	private ID_Check line;
	public Tracker tracker;
	private Vector<Kiosk> kiosks;
	/*
	private Kiosk kiosk;
	private ScanMachine scanMachine;
	*/
	public Stalker(ID_Check line,Tracker tracker, Vector<Kiosk> kiosks){
		this.line = line;
		this.kiosks = kiosks;
		this.tracker = tracker;
		new Thread(this).start();
	}
	
	public void run(){
		try{
			while(true){
				//System.out.println(line);
				for(int i=0;i<3;i++){
					System.out.println(kiosks.elementAt(i));
				}
				System.out.println(tracker);
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