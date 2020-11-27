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
	
	public Date getDate() {
		return date;
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	public String toString(){
		String str = date + "\n" + startTime;
		return str;
	}
}
