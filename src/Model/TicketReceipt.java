package Model;

public class TicketReceipt {
	private String email;
	private int ticketNumber;
	
	public TicketReceipt(String e, int tn) {
		email = e;
		ticketNumber= tn;
	}
	
	public String getEmail() {
		return email;
	}
	
	public int getTicketNumber() {
		return ticketNumber;
	}
}
