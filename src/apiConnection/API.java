package apiConnection;

import java.net.URI;
import java.net.http.HttpClient;

public class API {

	private static String url = null;
	private static URI uri;
	private static HttpClient httpClient = HttpClient.newHttpClient();
}
