package edu.eci.arsw.connection;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

public interface HttpConnectionService {
	
	public HttpResponse<JsonNode> totalNumbers ();

	public HttpResponse<JsonNode> statsNumbers ();
	
	public HttpResponse<JsonNode> countryNumbers (String pais); 
	
	public HttpResponse<JsonNode> countryStatsNumbers (String pais);


	
}
