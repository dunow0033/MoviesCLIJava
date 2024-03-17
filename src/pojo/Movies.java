package pojo;

import java.util.ArrayList;
import java.util.List;

public class Movies {
	
	private String title;
    private String director;
    private String writer;
    private String actors;
    private String plot;
    private String dateReleased;
    private String rating;
    private String awards;

    public Movies(String title, String director, String writer, String actors, String plot, String dateReleased, String rating, String awards) {
        this.title = title;
        this.director = director;
        this.writer = writer;
        this.actors = actors;
        this.plot = plot;
        this.dateReleased = dateReleased;
        this.rating = rating;
        this.awards = awards;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getWriter() {
        return writer;
    }

    public String getActors() {
        return actors;
    }

    public String getPlot() {
        return plot;
    }

    public String getDateReleased() {
        return dateReleased;
    }

    public String getRating() {
        return rating;
    }

    public String getAwards() {
        return awards;
    }

    public static List<Movies> movies = new ArrayList<>();
    
    public static List<Movies> allMovies() {
		return movies;
	}
    
    public static Movies last() {
    	if(!movies.isEmpty()) {
    		return movies.get(movies.size() - 1);
    	} else {
    		return null;
    	}
	}
}
