package edu.eci.arsw.cache;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.eci.arsw.model.DatosPais;
import edu.eci.arsw.model.DatosProvincia;

@Service
public class CoronavirusStatsCache {
    private HashMap<String,DatosPais> memoriaDatoPais = new HashMap<String,DatosPais>();

    private HashMap<String,List<DatosProvincia>> memoriaDatoProvincia = new HashMap<String,List<DatosProvincia>>();
    
    public void saveDatoPais(String pais, DatosPais datosPais){
        memoriaDatoPais.put(pais, datosPais);
    }

    public void saveDatoProvincia(String pais, List<DatosProvincia> datosProvincia){
        memoriaDatoProvincia.put(pais, datosProvincia);
    }

    public HashMap<String,DatosPais> getMemoriaDatoPais(){
        return memoriaDatoPais;
    }

    public HashMap<String,List<DatosProvincia>> getMemoriaDatoProvincia(){
        return memoriaDatoProvincia;
    }
}
