import java.util.Vector;
import Utilities.*;

class Voter implements Runnable{
	//local variables
	private String name;
	private Line line;
	private Kiosk kiosk;
	private ScanMachine scanMachine;
	
	public Voter(String name){
		this.name = name;
	}
	
	public void setLine(Line line){
		this.line = line;
	}
	public void setKiosk(Kiosk kiosk){
		this.kiosk = kiosk;
	}
	public void setScanMachine(ScanMachine scanMachine){
		this.scanMachine = scanMachine;
	}
	
	public void run(){
		
	}
}