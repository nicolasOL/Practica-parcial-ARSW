package edu.eci.arsw.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;

import edu.eci.arsw.services.CoronavirusStatsServices;

@RestController
@RequestMapping("/casos") 
public class CoronavirusStatsController {

    @Autowired
    CoronavirusStatsServices coronavirusStatsServices;


    @GetMapping
    public ResponseEntity<?> getAllCases(){
        try{
            String data = new Gson().toJson(coronavirusStatsServices.getAllCountryNumbers());
            return new ResponseEntity<>(data,HttpStatus.ACCEPTED);
        }catch(Exception ex){
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Recurso no encontrado",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{pais}")
    public ResponseEntity<?> getCasesByCountry(@PathVariable String pais){
        try{
            String data = new Gson().toJson(coronavirusStatsServices.countryStatsNumbers(pais));
            return new ResponseEntity<>(data,HttpStatus.ACCEPTED);
        }catch(Exception ex){
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Recurso no encontrado",HttpStatus.NOT_FOUND);
        }
    }


}
