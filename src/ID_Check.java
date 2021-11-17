import java.util.Vector;

class ID_Check{
	//this is set to the number of voters we will process, once it is 0, the helpers can exit
	public Tracker tracker;
	private Vector<Object> waitingVoters = new Vector<>();
	
	private Vector<Object> waitingHelpers = new Vector<>();
	private Vector<Object> busyHelpers = new Vector<>();
	
	private int helperCount;
	
	//constructor for monitor
	public ID_Check(Tracker tracker, int helperCount){
		this.tracker = tracker;
		this.helperCount = helperCount;
	}
	
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
	
	public boolean startHelping(String name){
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
			return false;
		}
		else if(this.tracker.lineVotersRemaining > helperCount && waitingVoters.isEmpty()){
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
			return false;
		}
		return true;
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
	
	public String toString(){
		return "Remaining voters:"+this.tracker.lineVotersRemaining+" Current Voter Line:"+this.waitingVoters.size()+" Current Busy Helpers:"+this.busyHelpers.size()+" Current Waiting Helpers:"+waitingHelpers.size();
	}
}