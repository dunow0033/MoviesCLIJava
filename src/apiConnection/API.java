package apiConnection;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import pojo.Movies;

public class API {

	private static String API_key = "e6fd8697";
	private static String url = null;
	private static URI uri;
	private static HttpClient httpClient = HttpClient.newHttpClient();
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	private static String title, director, writer, actors, plot, dateReleased, rating, awards;
	
	public static CompletableFuture<Movies> movieInfo(String name){
		url = "http://www.omdbapi.com/?apikey=" + API_key + "&t=" + name;
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.build();
		
		return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(API::parseResponse)
                .exceptionally(e -> {
                    e.printStackTrace();
                    return null;
                });
    }

    private static Movies parseResponse(String responseBody) {
        try {
        	
            JsonNode movieData = objectMapper.readTree(responseBody);
            
            String title = movieData.has("Title") ? movieData.get("Title").asText() : "";
            String director = movieData.has("Director") ? movieData.get("Director").asText() : "";
            String writer = movieData.has("Writer") ? movieData.get("Writer").asText() : "";
            String actors = movieData.has("Actors") ? movieData.get("Actors").asText() : "";
            String plot = movieData.has("Plot") ? movieData.get("Plot").asText() : "";
            String dateReleased = movieData.has("Released") ? movieData.get("Released").asText() : "";
            String rating = movieData.has("Rated") ? movieData.get("Rated").asText() : "";
            String awards = movieData.has("Awards") ? movieData.get("Awards").asText() : "";

            return new Movies(title, director, writer, actors, plot, dateReleased, rating, awards);
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static CompletableFuture<Boolean> checkForInvalidMovie(String name) {
    
    	HttpRequest request = HttpRequest.newBuilder()
    			.uri(URI.create(url))
    			.build();
    	
    	return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
    			.thenApply(HttpResponse::body)
    			.thenApply(API::parseInvalidMovieResponse)
    			.exceptionally(e -> {
    				e.printStackTrace();
    				return null;
    			});
    }
    
    public static Boolean parseInvalidMovieResponse(String responseBody) {
    	try {
    		JsonNode movieData = objectMapper.readTree(responseBody);
    		if(movieData.has("Response")) {
    			return movieData.get("Response").asText().equals("True");
    		}
    		return null;
    	} catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
}
