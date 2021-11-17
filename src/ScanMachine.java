import java.util.Vector;
/*
	
*/
class ScanMachine{
	//this is set to the number of voters we will process, once it is 0, the helpers can exit
	public Tracker tracker;
	//machine and helper count
	private int machineCount;
	private int helperCount;
	
	private boolean activeGroup = false;
	private int groupProgress = 0;
	//constructor for monitor
	public ScanMachine(Tracker tracker, int machineCount, int helperCount){
		this.tracker = tracker;
		this.machineCount = machineCount;
		this.helperCount = helperCount;
	}
	
	public void enterGroup(String name){
		/*
			this is where voters enter so that they can enter the group
			groups are divided by the machineCount
		*/
	}
	
	public void leaveGroup(){
		/*
			this is what voters call when they're done, it will increase the progress,
			decrement the remaining voters in the tracker, and alert the helpers that theyre done
		*/
	}
	
	public void startHelping(String name){
		/*
			this is what helpers call in their run functions
			they wait until a voter triggers them
		*/
	}
	private void releaseGroup(){
		/* this is what helpers use to release a group */
	}
	private void helpVoter(){
		
	}
	
	private void getHelp(){
		/*
			this is called by the voter when they need help, it pulls a helper out of their waiting queue
			and puts them in the busy queue
		*/
	}
}