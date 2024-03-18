package cli;

import java.util.Scanner;

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
		
		movie = sc.next();
	}
	
//	public String prompt_for_answer()
//	{
//		return sc.next();
//	}

}
