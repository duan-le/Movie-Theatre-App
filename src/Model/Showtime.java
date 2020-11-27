package Model;

public class Showtime {
	private Date date;
	private String startTime;
	private String endTime;

	public Showtime(Date d, String st, String et) {
		date = d;
		startTime = st;
		endTime = et;
	}
	
	public String toString(){
		String str = date + "\n" + startTime;
		return str;
	}
}
