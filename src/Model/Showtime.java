package Model;
import java.util.*;
import java.text.*;
public class Showtime {
	private Date date;

	public Showtime(Date d) {
		date = d;
	}
	
	public Date getDate() {
		return date;
	}

	public String toString(){
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm"); 
		String st = dt.format(date);
		return st;
	}
}
