package Model;

public class Showtime {
	private Date date;
	private String startTime;
	private String endTime;

	
	public String toString(){
		String str = date + "\n" + startTime;
		return str;
	}
}
