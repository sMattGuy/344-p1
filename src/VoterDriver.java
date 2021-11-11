public class VoterDriver{
	public static void main(String args[]){
		int numVoters = 20;
		int numIDHelpers = 3;
		
		ID_Check id_check = new ID_Check(numVoters);
		
		Stalker stalker = new Stalker(id_check);
		
		//create ID helpers
		for(int i=0;i<numIDHelpers;i++){
			ID_Checker checker = new ID_Checker("Checker_"+i,id_check);
		}
		//create voters
		for(int i=0;i<numVoters;i++){
			Voter voter = new Voter("Voter_"+i,id_check);
		}
	}
}