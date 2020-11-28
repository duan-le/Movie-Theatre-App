package Model;

public class Showtime {
	private Date date;
	private String startTime;

	public Showtime(Date d, String st) {
		date = d;
		startTime = st;
	}
	
	public Date getDate() {
		return date;
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public String toString(){
		String str = date + "\n" + startTime;
		return str;
	}
}
