package Model;

public class Seat {
	private int seatNumber;
	private boolean isAvailable;

	public Seat(int sn, boolean avail){
		seatNumber = sn;
		isAvailable = avail;
	}

	public int getSeatNumber() {
		return seatNumber;
	}
	
	public boolean getAvailability() {
		return isAvailable;
	}
}
