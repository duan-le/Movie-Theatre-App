package Model;

public class MovieNews {
	private String news;
	private Date publishDate;
	private Movie movie;
	
	public MovieNews(String n, Date pd, Movie m) {
		news = n;
		publishDate = pd;
		movie = m;
	}
	
	public String getNews() {
		return news;
	}
	
	public Date getPublishDate() {
		return publishDate;
	}
	
	public Movie getMovie() {
		return movie;
	}
}
