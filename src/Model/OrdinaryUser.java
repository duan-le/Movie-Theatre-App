package Model;

import java.util.ArrayList;

public class OrdinaryUser {
	private ArrayList<Ticket> ticketList;
	private ArrayList<TicketReceipt> receiptList;
	private ArrayList<Voucher> voucherList;
	
	public OrdinaryUser() {
		ticketList = new ArrayList<Ticket>();
		receiptList = new ArrayList<TicketReceipt>();
		voucherList = new ArrayList<Voucher>();
	}
	
	public void addTicketReceipt(TicketReceipt tr) {
		receiptList.add(tr);
	}
	
	public void addTicket(Ticket t) {
		ticketList.add(t);
	}
	
	public ArrayList<Ticket> getTicketList() {
		return ticketList;
	}
	
	public void clearTicketList() {
		ticketList.clear();
	}
}
