import java.util.Random;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

class Voter implements Runnable{
	//local variables
	public Tracker tracker;
	private String name;
	private ID_Check line;
	
	private Vector<Kiosk> kiosks;
	
	/*
	private ScanMachine scanMachine;
	*/
	
	public Voter(String name, ID_Check line, Tracker tracker, Vector<Kiosk> kiosks){
		this.name = name;
		this.line = line;
		this.tracker = tracker;
		this.kiosks = kiosks;
		new Thread(this).start();
	}
	
	public void run(){
		try{
			//id section
			this.wasteTime(1000,2000);
			this.msg("Entering the ID line");
			line.enterLine(this.name);
			this.msg("Getting ID checked");
			this.wasteTime(2000,5000);
			line.exitLine(this.name);
			
			//kiosk section
			this.msg("Moving on to Kiosk");
			this.wasteTime(1000,3000);
			this.msg("Looking for shortest kiosk line");
			int chosenKiosk = 0;
			for(int i=0;i<kiosks.size();i++){
				if(kiosks.elementAt(i).lineSize() < kiosks.elementAt(chosenKiosk).lineSize()){
					chosenKiosk = i;
				}
			}
			this.msg("Selected kiosk "+chosenKiosk);
			kiosks.elementAt(chosenKiosk).enterLine(this.name);
			this.wasteTime(2000,5000);
			kiosks.elementAt(chosenKiosk).exitLine(this.name);
			
			this.msg("Moving on to Scan Machine");
			this.msg("Done, leaving the voting area (exiting)");
		}
		catch(InterruptedException e){
			System.out.println(e);
		}
	}
	
	public static long time = System.currentTimeMillis();
	
	public void msg(String m) {
		System.out.println("["+(System.currentTimeMillis()-time)+"] "+this.name+": "+m);
	}
	
	private void wasteTime(int min,int max) throws InterruptedException{
		Random rand = new Random(System.currentTimeMillis());
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(max-min)+min);
	}
}