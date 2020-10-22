package edu.eci.arsw.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import edu.eci.arsw.cache.CoronavirusStatsCache;
import edu.eci.arsw.connection.HttpConnectionService;
import edu.eci.arsw.services.CoronavirusStatsServices;
import edu.eci.arsw.model.DatosPais;
import edu.eci.arsw.model.DatosProvincia;

@Service
public class CoronavirusStatsServicesImpl implements CoronavirusStatsServices {

	@Autowired
	public HttpConnectionService connectionHttp;

	@Autowired
	public CoronavirusStatsCache coronavirusStatsCache;

	@Override
	public HashMap<String, String> globalNumbers() {
		HttpResponse<JsonNode> res = connectionHttp.totalNumbers();
		JSONObject r = res.getBody().getObject().getJSONObject("data");

		HashMap<String, String> resp = new HashMap<String, String>();

		// HashMap<String,String> tempMuertes = new HashMap<String,String>();
		resp.put("muertes", r.getString("deaths"));
		// resp.add(tempMuertes);

		// HashMap<String,String> tempInfectados = new HashMap<String,String>();
		resp.put("infectados", r.getString("confirmed"));
		// resp.add(tempInfectados);

		// HashMap<String,String> tempCurados = new HashMap<String,String>();
		resp.put("curados", r.getString("recovered"));
		// resp.add(tempCurados);

		return resp;
	}

	@Override
	public HashMap<String, List<HashMap<String, String>>> statsNumbers() {
		return null;
	}

	@Override
	public HashMap<String, String> countryNumbers(String pais) {
		HttpResponse<JsonNode> res = connectionHttp.countryNumbers(pais);
		JSONObject r = res.getBody().getObject().getJSONObject("data");

		HashMap<String, String> resp = new HashMap<String, String>();

		// HashMap<String,String> tempMuertes = new HashMap<String,String>();
		resp.put("muertes", Long.toString(r.getLong("deaths")));
		// resp.add(tempMuertes);

		// HashMap<String,String> tempInfectados = new HashMap<String,String>();
		resp.put("infectados", Long.toString(r.getLong("confirmed")));
		// resp.add(tempInfectados);

		// HashMap<String,String> tempCurados = new HashMap<String,String>();
		resp.put("curados", Long.toString(r.getLong("recovered")));
		// resp.add(tempCurados);

		return resp;
	}

	@Override
	public List<DatosProvincia> countryStatsNumbers(String pais) {

		// HashMap<String,HashMap<String,String>> resp = new
		// HashMap<String,HashMap<String,String>>();

		List<DatosProvincia> resp = new ArrayList<DatosProvincia>();

		HttpResponse<JsonNode> stats = connectionHttp.countryStatsNumbers(pais);
		JSONArray js = stats.getBody().getObject().getJSONObject("data").getJSONArray("covid19Stats");

		for (int i = 0; i < js.length(); i++) {
			String curadosProvincia = "0";
			String nombreProvincia = js.getJSONObject(i).getString("province");
			String muertesProvincia = Long.toString(js.getJSONObject(i).getLong("deaths"));
			String infectadosProvincia = Long.toString(js.getJSONObject(i).getLong("confirmed"));
			curadosProvincia = Long.toString(js.getJSONObject(i).optLong("recovered", 0));
			

			System.out.println(nombreProvincia+" "+muertesProvincia+" "+infectadosProvincia+" "+curadosProvincia);
			DatosProvincia datosProvincia = new DatosProvincia(nombreProvincia, muertesProvincia, curadosProvincia, infectadosProvincia);

			resp.add(datosProvincia);

		}
		coronavirusStatsCache.saveDatoProvincia(pais, resp);

		return resp;
	}

	private List<String> getProvincias(String pais) {
		List<String> res = new ArrayList<String>();
		HttpResponse<JsonNode> stats = connectionHttp.countryStatsNumbers(pais);
		JSONArray js = stats.getBody().getObject().getJSONArray("covid19Stats");

		for (int i = 0; i < js.length(); i++) {
			res.add(js.getJSONObject(i).getString("province"));
		}

		return res;

	}

	@Override
	public String[] getAllCountries() {
		String[] resp = { "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua%20and%20Barbuda",
				"Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh",
				"Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia%20and%20Herzegovina",
				"Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina%20Faso", "Burma", "Burundi", "Cabo%20Verde",
				"Cambodia", "Cameroon", "Canada", "Central%20African%20Republic", "Chad", "Chile", "China", "Colombia",
				"Comoros", "Congo%20(Brazzaville)", "Congo%20(Kinshasa)", "Costa%20Rica", "Cote%20d'Ivoire", "Croatia",
				"Cuba", "Cyprus", "Czechia", "Denmark", "Diamond%20Princess", "Djibouti", "Dominica",
				"Dominican%20Republic", "Ecuador", "Egypt", "El%20Salvador", "Equatorial%20Guinea", "Eritrea",
				"Estonia", "Eswatini", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany",
				"Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Holy%20See",
				"Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy",
				"Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Korea", "South", "Kosovo", "Kuwait", "Kyrgyzstan",
				"Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg",
				"MS%20Zaandam", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Mauritania",
				"Mauritius", "Mexico", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique",
				"Namibia", "Nepal", "Netherlands", "New%20Zealand", "Nicaragua", "Niger", "Nigeria",
				"North%20Macedonia", "Norway", "Oman", "Pakistan", "Panama", "Papua%20New%20Guinea", "Paraguay", "Peru",
				"Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda",
				"Saint%20Kitts%20and%20Nevis", "Saint%20Lucia", "Saint%20Vincent%20and%20the%20Grenadines",
				"San%20Marino", "Sao%20Tome%20and%20Principe", "Saudi%20Arabia", "Senegal", "Serbia", "Seychelles",
				"Sierra%20Leone", "Singapore", "Slovakia", "Slovenia", "Solomon%20Islands", "Somalia", "South%20Africa",
				"South%20Sudan", "Spain", "Sri%20Lanka", "Sudan", "Suriname", "Sweden", "Switzerland", "Syria",
				"Taiwan*", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Trinidad%20and%20Tobago",
				"Tunisia", "Turkey", "US", "Uganda", "Ukraine", "United%20Arab%20Emirates", "United%20Kingdom",
				"Uruguay", "Uzbekistan", "Venezuela", "Vietnam", "West%20Bank%20and%20Gaza", "Western%20Sahara",
				"Yemen", "Zambia", "Zimbabwe" };
		List<String> paises = new ArrayList<String>();
		HttpResponse<JsonNode> res = connectionHttp.statsNumbers();
		JSONArray r = res.getBody().getObject().getJSONObject("data").getJSONArray("covid19Stats");
		for (int i = 0; i < r.length(); i++) {
			String pais = r.getJSONObject(i).getString("country");
			if (!paises.contains(pais)) {
				paises.add(pais);
			}
		}

		return resp;

	}

	@Override
	public List<DatosPais> getAllCountryNumbers() {
		// String[] paises = getAllCountries();

		String[] paises = { "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua%20and%20Barbuda",
				"Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh",
				"Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia%20and%20Herzegovina",
				"Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina%20Faso", "Burma", "Burundi", "Cabo%20Verde",
				"Cambodia", "Cameroon", "Canada", "Central%20African%20Republic", "Chad", "Chile", "China", "Colombia",
				"Comoros", "Congo%20(Brazzaville)", "Congo%20(Kinshasa)", "Costa%20Rica", "Cote%20d'Ivoire", "Croatia",
				"Cuba", "Cyprus", "Czechia", "Denmark", "Diamond%20Princess", "Djibouti", "Dominica",
				"Dominican%20Republic", "Ecuador", "Egypt", "El%20Salvador", "Equatorial%20Guinea", "Eritrea",
				"Estonia", "Eswatini", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany",
				"Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Holy%20See",
				"Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy",
				"Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Korea", "South", "Kosovo", "Kuwait", "Kyrgyzstan",
				"Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg",
				"MS%20Zaandam", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Mauritania",
				"Mauritius", "Mexico", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique",
				"Namibia", "Nepal", "Netherlands", "New%20Zealand", "Nicaragua", "Niger", "Nigeria",
				"North%20Macedonia", "Norway", "Oman", "Pakistan", "Panama", "Papua%20New%20Guinea", "Paraguay", "Peru",
				"Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda",
				"Saint%20Kitts%20and%20Nevis", "Saint%20Lucia", "Saint%20Vincent%20and%20the%20Grenadines",
				"San%20Marino", "Sao%20Tome%20and%20Principe", "Saudi%20Arabia", "Senegal", "Serbia", "Seychelles",
				"Sierra%20Leone", "Singapore", "Slovakia", "Slovenia", "Solomon%20Islands", "Somalia", "South%20Africa",
				"South%20Sudan", "Spain", "Sri%20Lanka", "Sudan", "Suriname", "Sweden", "Switzerland", "Syria",
				"Taiwan*", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Trinidad%20and%20Tobago",
				"Tunisia", "Turkey", "US", "Uganda", "Ukraine", "United%20Arab%20Emirates", "United%20Kingdom",
				"Uruguay", "Uzbekistan", "Venezuela", "Vietnam", "West%20Bank%20and%20Gaza", "Western%20Sahara",
				"Yemen", "Zambia", "Zimbabwe" };

		// HashMap<String, HashMap<String, String>> resp = new HashMap<String,
		// HashMap<String, String>>();

		List<DatosPais> respFinal = new ArrayList<DatosPais>();

		for (int i = 0; i < paises.length; i++) {
			HashMap<String, String> paisTemp = countryNumbers(paises[i]);
			String muertes = paisTemp.get("muertes");
			String infectados = paisTemp.get("infectados");
			String curados = paisTemp.get("curados");
			String pais = paises[i].replace("%20", " ");
			DatosPais datosPais = new DatosPais(pais, muertes, infectados, curados);
			respFinal.add(datosPais);
		}

		return respFinal;
	}

}
