package Model;

public class Ticket {
	private String movieName;
	private int seatNumber;
	private int ticketNumber;
	private Showtime showtime;

	public Ticket(String movieName, Showtime showtime, int seatNumber){
		this.movieName = movieName;
		this.showtime = showtime;
		this.seatNumber = seatNumber;
	}
}
