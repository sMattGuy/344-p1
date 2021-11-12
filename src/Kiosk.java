import java.util.Vector;

class Kiosk{
	//name for the kiosk
	private int kioskNum = 0;
	//this is the kiosk line
	private Vector<Object> waitingVoters = new Vector<>();
	//tracker to keep a number on how many voters are left
	public Tracker tracker;
	
	private KioskHelper helper;
	//thread that is only for that kiosk
	private class KioskHelper implements Runnable{
		private String name;
		private Kiosk kiosk;
		
		public KioskHelper(String name, Kiosk kiosk){
			this.name = name;
			this.kiosk = kiosk;
		}
		
		public void run(){
			try{
				while(kiosk.tracker.kioskVotersRemaining != 0){
					//loop helpers job until voters are all gone
				}
			}
			catch(InterruptedException e){
				System.out.println(e);
			}
		}
	}
	
	public Kiosk(Tracker tracker){
		this.tracker = tracker;
		helper = new KioskHelper("KioskHelper_"+kioskNum);
	}
	//essentially same code as in ID_Check, except that there will only ever be one helper per kiosk
	//service methods for voter
	public void enterLine(String name){
		//object that thread will wait on
		Object convey = new Object();
		synchronized(convey){
			//voter enters line
			waitingVoters.addElement(convey);
			while(true){
				try{
					alertHelpers();
					convey.wait();
					break;
				}
				catch(InterruptedException e){
					continue;
				}
			}
		}
		//voter exits, sleeps, and calls exitLine
	}
	
	public synchronized void exitLine(String name){
		this.tracker.lineVotersRemaining--;
		alertBusyHelper();
	}
	
	public void startHelping(String name){
		if(!waitingVoters.isEmpty()){
			//assist voter
			alertVoters();
			//wait for voter to end
			Object convey = new Object();
			synchronized(convey){
				busyHelpers.addElement(convey);
				while(true){
					try{
						convey.wait();
						break;
					}
					catch(InterruptedException e){
						continue;
					}
				}
			}
		}
		else if(waitingVoters.isEmpty()){
			//no one to help, wait
			Object convey = new Object();
			synchronized(convey){
				waitingHelpers.addElement(convey);
				while(true){
					try{
						convey.wait();
						break;
					}
					catch(InterruptedException e){
						continue;
					}
				}
			}
		}
	}
	
	private synchronized void alertBusyHelper(){
		if(!busyHelpers.isEmpty()){
			synchronized(busyHelpers.elementAt(0)){
				busyHelpers.elementAt(0).notify();
				busyHelpers.removeElementAt(0);
			}
		}
	}
	private synchronized void alertVoters(){
		if(!waitingVoters.isEmpty()){
			synchronized(waitingVoters.elementAt(0)){
				waitingVoters.elementAt(0).notify();
				waitingVoters.removeElementAt(0);
			}
		}
	}
	private synchronized void alertHelpers(){
		if(!waitingHelpers.isEmpty()){
			synchronized(waitingHelpers.elementAt(0)){
				waitingHelpers.elementAt(0).notify();
			}
			waitingHelpers.removeElementAt(0);
		}
	}
}