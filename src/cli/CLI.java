package cli;

import java.util.Scanner;

import apiConnection.API;
import pojo.Movies;

public class CLI {
	
	private String movie;
	private Scanner sc = new Scanner(System.in);
	
	public void first()
	{
		greet_user();
	}
	
	public void greet_user()
	{
		System.out.println();
		System.out.println("Welcome to the movie trivia app!!");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Please enter a movie title:  ");
		
		sc.useDelimiter("\\n");
		
		movie = sc.next();
		
		while(!movie.equalsIgnoreCase("q"))
		{
			while(API.checkForInvalidMovie(movie) == null)
			{
				System.out.println();
				System.out.println("Invalid movite title, please try again (or 'q' to quit): ");
				movie = sc.next();
			}
		}
		
		API.movieInfo(movie).join();
		
		display_movie_data();
		
		System.out.println("Would you like to see additonal info for that movie or see trivia for another movie? ('y' or 'n'):  ");
	
		additional_movie_answer();
	}
	
//	public String prompt_for_answer()
//	{
//		return sc.next();
//	}
	
	public static void display_movie_data()
	{

		System.out.println();
		System.out.println("Movie: " + Movies.last().getTitle());
		System.out.println("Director: " + Movies.last().getDirector());
		System.out.println("Date Released: " + Movies.last().getDateReleased());
		System.out.println("Rating: " + Movies.last().getRating());
		System.out.println();
		System.out.println();
	}
	
	public void additional_movie_answer()
	{
		String answer = sc.next();

		if (answer.equalsIgnoreCase("y"))
			additional_movie_data();
		else if (answer.equalsIgnoreCase("n"))
		{
			System.out.println();
			System.out.println();
			greet_user();
		}
		else
		{
			System.out.println("Sorry, I didn't understand that response!!  Let's go back to the beginning!!");
			System.out.println();
			greet_user();
		}
	}
	
	public static void additional_movie_data()
	{
		System.out.println();
		System.out.println("Writer: " + Movies.last().getWriter());
		System.out.println("Actors: " + Movies.last().getActors());
		System.out.println("Plot: " + Movies.last().getPlot());
		System.out.println("Awards: " + Movies.last().getAwards());
		System.out.println();
		System.out.println();
	}
}
