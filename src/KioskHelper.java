import java.util.Random;
import java.util.concurrent.TimeUnit;

public class KioskHelper implements Runnable{
	private String name;
	private Kiosk kiosk;
	
	public Tracker tracker;
	
	public KioskHelper(String name, Kiosk kiosk, Tracker tracker){
		this.name = name;
		this.kiosk = kiosk;
		this.tracker = tracker;
		new Thread(this).start();
	}
	
	public void run(){
		try{
			while(tracker.kioskVotersRemaining != 0){
				this.msg("Ready to help next voter to kiosk");
				//loop helpers job until voters are all gone
				if(kiosk.startHelping(this.name)){break;};
				this.msg("Waiting for voter to finish at kiosk");
				this.wasteTime(1000,2000);
			}
			this.msg("Done helping voters at kiosk, leaving");
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