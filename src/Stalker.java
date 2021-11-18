import java.util.Vector;
import java.util.concurrent.TimeUnit;

class Stalker implements Runnable{
	//local variables
	private ID_Check line;
	public Tracker tracker;
	private Vector<Kiosk> kiosks;
	private Kiosk kiosk;
	private ScanMachine scanner;
	public Stalker(ID_Check line,Tracker tracker, Vector<Kiosk> kiosks, ScanMachine scanner){
		this.line = line;
		this.kiosks = kiosks;
		this.tracker = tracker;
		this.scanner = scanner;
		new Thread(this).start();
	}
	
	public void run(){
		try{
			while(true){
				//System.out.println(line);
				/*
				for(int i=0;i<3;i++){
					System.out.println(kiosks.elementAt(i));
				}
				*/
				System.out.println(scanner);
				System.out.println(tracker);
				wasteTime(3000);
			}
		}
		catch(InterruptedException e){
			
		}
	}
	
	private void wasteTime(int time) throws InterruptedException{
		TimeUnit.MILLISECONDS.sleep(time);
	}
}