import java.util.Vector;
public class VoterDriver{
	public static void main(String args[]){
		int numVoters = 20;
		
		int numIDHelpers = 3;
		
		int numKiosks = 3;
		
		int numScanMachines = 4;
		int numScanHelpers = 2;
		
		Tracker tracker = new Tracker(numVoters);
		
		ScanMachine scanner = new ScanMachine(tracker, numScanMachines, numScanHelpers);
		
		//create id line
		ID_Check id_check = new ID_Check(tracker, numIDHelpers);
		//create kiosks
		Vector<Kiosk> kiosks = new Vector<>();
		for(int i=0;i<numKiosks;i++){
			kiosks.addElement(new Kiosk(i,tracker));
		}
		//create ID helpers
		for(int i=0;i<numIDHelpers;i++){
			ID_Checker checker = new ID_Checker("Checker_"+i,id_check,tracker);
		}
		//create scan helpers
		for(int i=0;i<numScanHelpers;i++){
			ScanHelper scanhelp = new ScanHelper("ScanHelper_"+i,scanner,tracker);
		}
		//create voters
		for(int i=0;i<numVoters;i++){
			Voter voter = new Voter("Voter_"+i,id_check,tracker,kiosks,scanner);
		}
		//Stalker stalker = new Stalker(id_check,tracker,kiosks,scanner);
	}
}