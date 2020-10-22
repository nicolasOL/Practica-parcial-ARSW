package edu.eci.arsw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import edu.eci.arsw.connection.HttpConnectionService;
import edu.eci.arsw.services.CoronavirusStatsServices;

@SpringBootTest
class ArswApplicationTests {
	@Autowired
	CoronavirusStatsServices t;

	@Autowired
	HttpConnectionService ht;

	@Autowired
	CoronavirusStatsServices cs;

	@Test
	void contextLoads() {
	}

	@Test
	public void shouldReturnAllCases() throws UnirestException {
		HttpResponse<JsonNode> response = Unirest.get("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats")
				.header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
				.header("x-rapidapi-key", "34f05cff54msh30ba6f36c91c183p166499jsn555917ef62b8").asJson();

		assertNotNull(response.getBody());
		assertEquals((int) HttpStatus.SC_OK, response.getStatus());
		// System.out.println(response.getBody().toString());
	}

	@Test
	public void shouldReturnCaseByCountry() throws UnirestException {
		HttpResponse<JsonNode> response = Unirest
				.get("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country=Canada")
				.header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
				.header("x-rapidapi-key", "34f05cff54msh30ba6f36c91c183p166499jsn555917ef62b8").asJson();

		assertNotNull(response.getBody());
		assertEquals((int) HttpStatus.SC_OK, response.getStatus());
		// System.out.println(response.getBody().toString());
	}

	@Test
	public void shouldWork() throws UnirestException, JSONException {
		//List<String> a=t.totalNumbers();
		//HashMap<String,List<HashMap<String,String>>> b=t.statsNumbers();
		//System.out.println(a.toString());
		//System.out.println(b.toString());

		//System.out.println(cs.countryStatsNumbers("Colombia"));

		cs.countryStatsNumbers("Colombia");

		//System.out.println(ht.statsNumbers().getBody().getObject().getJSONObject("data").getJSONArray("covid19Stats").getJSONObject(0).getString("recovered"));
		
	}

}
