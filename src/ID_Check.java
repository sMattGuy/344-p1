import java.util.Vector;

class ID_Check{
	//this is set to the number of voters we will process, once it is 0, the helpers can exit
	private int numVoters = 0;
	
	private Vector<Object> waitingVoters = new Vector<>();
	private Vector<Object> busyHelpers = new Vector<>();
	
	//constructor for monitor
	public ID_Check(int numVoters){
		this.numVoters = numVoters;
	}
	
	//service methods for voter
	public void enterLine(String name){
		//object that thread will wait on
		Object convey = new Object();
		synchronized(convey){
			System.out.println(name + " is entering the line");
			//voter enters line
			waitingVoters.addElement(convey);
			System.out.println(this);
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
		System.out.println(name + " is exiting the line");
		//voter exits, sleeps, and calls exitLine
	}
	
	public void exitLine(String name){
		System.out.println(name + " is moving to the kiosk");
		this.numVoters--;
		synchronized(busyHelpers.elementAt(0)){
			busyHelpers.elementAt(0).notify();
		}
		busyHelpers.removeElementAt(0);
	}
	
	public synchronized void startHelping(String name){
		if(!waitingVoters.isEmpty()){
			System.out.println(name + " is helping a voter");
			//assist voter
			synchronized(waitingVoters.elementAt(0)){
				waitingVoters.elementAt(0).notify();
			}
			waitingVoters.removeElementAt(0);
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
	}
	
	//use this to determine if all voters are done in line, then helpers can terminate
	public int getRemainingVoters(){
		return this.numVoters;
	}
	
	public String toString(){
		return "Remaining voters:"+this.numVoters+" Current Voter Line:"+this.waitingVoters.size()+" Current Busy Helpers:"+this.busyHelpers.size();
	}
}