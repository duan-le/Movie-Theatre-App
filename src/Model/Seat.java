package Model;

public class Seat {
	private int seatNumber;
	private boolean isAvailable;

	public Seat(int seatNumber){
		this.seatNumber = seatNumber;
		this.isAvailable = false;
	}

	public int getSeatNumber() {
		return seatNumber;
	}
}
