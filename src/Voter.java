import java.util.Random;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

class Voter implements Runnable{
	//local variables
	private String name;
	private ID_Check line;
	public Tracker tracker;
	/*
	private Kiosk kiosk;
	private ScanMachine scanMachine;
	*/
	public Voter(String name, ID_Check line, Tracker tracker){
		this.name = name;
		this.line = line;
		this.tracker = tracker;
		new Thread(this).start();
	}
	
	public void run(){
		try{
			this.msg("Entering the ID line");
			line.enterLine(this.name);
			this.msg("Getting ID checked");
			this.wasteTime(1000);
			line.exitLine(this.name);
			this.msg("Moving on to Kiosk");
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
	
	private void wasteTime(int time) throws InterruptedException{
		Random rand = new Random(System.currentTimeMillis());
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(time));
	}
}