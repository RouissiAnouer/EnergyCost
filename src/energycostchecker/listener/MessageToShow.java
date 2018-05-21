package energycostchecker.listener;



public class MessageToShow {
	
	String Message;

	public String CalculateSum(int sum) {
		if (sum<10) {
			Message="Charge Acceptable";
			
		}
		else if((sum>=10 && sum<30)) {
			Message="Charge Importante";
		}
		else if(sum>30) {
			Message="Charge Exeger";
		}
		return Message;
	}

}
