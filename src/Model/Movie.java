package Model;

public class Movie {
	private String name;
	private String genre;
	private Date releaseDate;
	private String runningTime;
	
	public Movie(String n, String g, Date rd, String rt) {
		name = n;
		genre = g;
		releaseDate = rd;
		runningTime = rt;
	}
}
