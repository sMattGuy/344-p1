import java.util.Vector;
public class VoterDriver{
	public static void main(String args[]){
		int numVoters = 10;
		int numIDHelpers = 3;
		int numKiosks = 3;
		
		Tracker tracker = new Tracker(numVoters);
		
		ID_Check id_check = new ID_Check(tracker);
		
		
		Vector<Kiosk> kiosks = new Vector<>();
		for(int i=0;i<numKiosks;i++){
			kiosks.addElement(new Kiosk(i,tracker));
		}
		//create ID helpers
		for(int i=0;i<numIDHelpers;i++){
			ID_Checker checker = new ID_Checker("Checker_"+i,id_check,tracker);
		}
		//create voters
		for(int i=0;i<numVoters;i++){
			Voter voter = new Voter("Voter_"+i,id_check,tracker,kiosks);
		}
		//Stalker stalker = new Stalker(id_check,tracker,kiosks);
	}
}