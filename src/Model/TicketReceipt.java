package Model;
import java.util.*;

public class TicketReceipt {
	private int ticketNumber;
	private int cardNumber;
	
	public TicketReceipt(int tn, int cn) {
		ticketNumber = tn;
		cardNumber = cn;
	}
	
	public int getTicketNumber() {
		return ticketNumber;
	}
	public int getCardNumber() {
		return cardNumber;
	}
}
