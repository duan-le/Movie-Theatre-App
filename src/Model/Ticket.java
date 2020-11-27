package Model;

public class Ticket {
	private String movieName;
	private int seatNumber;
	private int ticketNumber;
	private Showtime showtime;

	public Ticket(String mn, int sn, int tn, Showtime st) {
		movieName = mn;
		seatNumber = sn;
		ticketNumber = tn;
		showtime = st;
	}
	
	public String getMovieName() {
		return movieName;
	}
	
	public int getSeatNumber() {
		return seatNumber;
	}
	
	public int getTicketNumber() {
		return ticketNumber;
	}
	
	public Showtime getShowtime() {
		return showtime;
	}
}
