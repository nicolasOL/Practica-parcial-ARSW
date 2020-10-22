package edu.eci.arsw.connection.impl;

import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import edu.eci.arsw.connection.HttpConnectionService;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class HttpConnectionServiceImpl implements HttpConnectionService {
	
	@Override
	public HttpResponse<JsonNode> totalNumbers () {
		HttpResponse<JsonNode> response=null;
		try {
			response = Unirest.get("https://rapidapi.p.rapidapi.com/v1/total")
					.header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
					.header("x-rapidapi-key", "e99c72dd56msh13af4ebb49cd037p18760fjsna447e334494b")
					.asJson();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	@Override
	public HttpResponse<JsonNode> statsNumbers () {
		HttpResponse<JsonNode> response=null;
		try {
			response = Unirest.get("https://rapidapi.p.rapidapi.com/v1/stats")
					.header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
					.header("x-rapidapi-key", "e99c72dd56msh13af4ebb49cd037p18760fjsna447e334494b")
					.asJson();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	@Override
	public HttpResponse<JsonNode> countryNumbers (String pais) {
		HttpResponse<JsonNode> response=null;
		try {
			response = Unirest.get("https://rapidapi.p.rapidapi.com/v1/total?country="+pais)
					.header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
					.header("x-rapidapi-key", "e99c72dd56msh13af4ebb49cd037p18760fjsna447e334494b")
					.asJson();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	@Override
	public HttpResponse<JsonNode> countryStatsNumbers (String pais) {
		HttpResponse<JsonNode> response=null;
		try {
			response = Unirest.get("https://rapidapi.p.rapidapi.com/v1/stats?country="+pais)
					.header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
					.header("x-rapidapi-key", "e99c72dd56msh13af4ebb49cd037p18760fjsna447e334494b")
					.asJson();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	
}
