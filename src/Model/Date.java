package Model;

public class Date {
	private int day;
	private int month;
	private int year;

	public Date(int d, int m, int y) {
		day = d;
		month = m;
		year = y;
	}
	
	public int getDay() {
		return day;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getYear() {
		return year;
	}
	
	public String toString() {
		String str = month + "/" + day + "/" + year;
		return str;
	}
}
