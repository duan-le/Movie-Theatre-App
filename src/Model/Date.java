package Model;

public class Date {
	private int day;
	private int month;
	private int year;

	public String toString() {
		String str = month + "/" + day + "/" + year;
		return str;
	}
}
