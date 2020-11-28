package Model;
import java.util.*;
public class Movie {
	private String name;
	private String genre;
	private Date releaseDate;
	private String runningTime;

	public Movie(){
		
	}
	public Movie(String n, String g, Date rd, String rt) {
		name = n;
		genre = g;
		releaseDate = rd;
		runningTime = rt;
	}
	
	public String getName() {
		return name;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public Date getReleaseDate() {
		return releaseDate;
	}
	
	public String getRunningTime() {
		return runningTime;
	}
}
