import java.util.Vector;
import java.util.concurrent.TimeUnit;

class ID_Checker implements Runnable{
	//local variables
	private String name;
	private ID_Check line;
	
	public ID_Checker(String name, ID_Check line){
		this.name = name;
		this.line = line;
		new Thread(this).start();
	}
	
	public void setLine(ID_Check line){
		this.line = line;
	}
	
	public void run(){
		try{
			while(this.line.getRemainingVoters() != 0){
				//loop until all voters are done
				line.startHelping(this.name);
				this.wasteTime(500);
			}
		}
		catch(InterruptedException e){
			System.out.println(e);
		}
	}
	
	private void wasteTime(int time) throws InterruptedException{
		TimeUnit.MILLISECONDS.sleep(time);
	}
}