package com.thecollective.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thecollective.exception.RecordNotFoundException;
import com.thecollective.model.PowerPlanet;
import com.thecollective.service.PowerPlanetService;
 
@RestController
@Validated
@RequestMapping("/powerplanet")
public class PowerPlanetController
{
    @Autowired
    PowerPlanetService service;
 
    private static final Logger logger = LogManager.getLogger(PowerPlanetController.class);
    
    @GetMapping
    public ResponseEntity<List<PowerPlanet>> getAllPowerPlanets() {
        List<PowerPlanet> list = service.getAllPowerPlanets();
        return new ResponseEntity<List<PowerPlanet>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PowerPlanet> getPowerPlanetById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
    	PowerPlanet entity = service.getPowerPlanetById(id);
 
        return new ResponseEntity<PowerPlanet>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/locationcode")
    public ResponseEntity<List<PowerPlanet>> getPowerPlanetByLocationCode(@RequestParam String code, @RequestParam(required = false) @Min(0) @Max(100) Integer page)
                                                   throws RecordNotFoundException {
    	logger.info("locationCode= "+ code + " , page=  " + page);
    	
    	Pageable pagging = null;
    	List<PowerPlanet> entities = null;
    	if(code.length() == 0 && page == null) {
    		//list all
    		return getAllPowerPlanets();
    	}else if(code.length() > 0 && page == null)  {
    		//list all based on code without pagination
    		entities = service.getPowerPlanetByLocationCode(code);
    	}else if(code.length() == 0 && page != null){
    		//list all with pagination
    		pagging = PageRequest.of(page, 10);
    		entities = service.getAllPowerPlanets(pagging);
    	}else {
    		//list all based on code with pagination
    		pagging = PageRequest.of(page, 10);
    		entities = service.getPowerPlanetByLocationCode(code,pagging);
    	}return new ResponseEntity<List<PowerPlanet>>(entities, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/getgenerationbylocation")
    public ResponseEntity<Map<String, String>>  getConsumption(@RequestParam String code) throws ArithmeticException,RecordNotFoundException{
    	Map<String, String> results = null;
    	try {
    		results = service.getGenerationAmount(code);
    		return new ResponseEntity<Map<String, String>>(results, new HttpHeaders(), HttpStatus.OK);
    	}catch(Exception e) {
    		results = new HashMap<String, String>();
    		results.put("Status","Failed");
    		results.put("Message", "No Records Found For This Location Code");
    		return new ResponseEntity<Map<String, String>>(results,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	
    }
    
     
}