import java.util.Vector;

class ID_Check{
	//this is set to the number of voters we will process, once it is 0, the helpers can exit
	private int numVoters = 0;
	
	private Vector<Voter> waitingVoters = new Vector<>();
	private Vector<ID_Checker> waitingHelpers = new Vector<>();
	
	//constructor for monitor
	public ID_Check(int numVoters){
		this.numVoters = numVoters;
	}
	//service methods
	public void enterLine(String name){
		//similar setup to rwcv
		Object convey = new Object();
		synchronized(convey){
			//add voter object to wait line
			waitingVoters.add(convey);
			while(true){
				//while loop to cover race condition covered in textbook
				try{
					convey.wait();
					break;
				}catch(InterruptedException e){
					continue;
				}
			}
		}
		//once exits the voter can sleep to simulate checking id
	}
	
	public void startHelping(String name){
		Object convey = new Object();
		//this is where the waiting helpers will enter, helpers will first check if the voter line is empty, if it isnt then they will help the first person in line, if it is they will wait in their queue. They will be notified by voters entering the line.
		synchronized(convey){
			if(waitingVoters.isEmpty()){
				//no voters to help, the helpers will enter their waiting queue
				while(true){
					//while loop to cover race condition covered in textbook
					try{
						convey.wait();
						break;
					}catch(InterruptedException e){
						continue;
					}
				}
			}
		}
	}
	
	public String toString(){
		
	}
}

/*
	notes
	ID_checkers are threads that unblock the voter threads
	voters can just wait until a id checker notifies them
	then the voter can sleep a bit to simulate checking id
	
	essentially a voter comes in and is put in the vector
	when that vector is not empty 
	
	voter enters line, waits
	id checker sees line is not empty -> notifies first voter -> id checker waits
	voter sleeps -> notifies their id checker
	voter can move on to next section, id checker can repeat checking the line if its not empty
*/