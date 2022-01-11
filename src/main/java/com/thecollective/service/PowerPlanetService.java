package com.thecollective.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thecollective.exception.RecordNotFoundException;
import com.thecollective.model.PowerPlanet;
import com.thecollective.repository.PowerPlanetRepository;
 
@Service
public class PowerPlanetService {
     
    @Autowired
    PowerPlanetRepository repository;
    
    
    private static final Logger logger = LogManager.getLogger(PowerPlanetService.class);
    
    
    public List<PowerPlanet> getAllPowerPlanets()
    {
        List<PowerPlanet> powerPlanetList = repository.findAll();
         
        if(powerPlanetList.size() > 0) {
            return powerPlanetList;
        } else {
            return new ArrayList<PowerPlanet>();
        }
    }
     
    public List<PowerPlanet> getAllPowerPlanets(Pageable p)
    {
        List<PowerPlanet> powerPlanetList = (repository.findAll(p)).getContent();
         
        if(powerPlanetList.size() > 0) {
            return powerPlanetList;
        } else {
            return new ArrayList<PowerPlanet>();
        }
    }
    
    
    public PowerPlanet getPowerPlanetById(Long id) throws RecordNotFoundException
    {
        Optional<PowerPlanet> powerPlanet = repository.findById(id);
         
        if(powerPlanet.isPresent()) {
            return powerPlanet.get();
        } else {
            throw new RecordNotFoundException("No power planet record exist for given id");
        }
    }
    
    
    public List<PowerPlanet> getPowerPlanetByLocationCode(String locationCode, Pageable p) throws RecordNotFoundException
    {
    	logger.info("locationCode= {} , pageNumber= {} , pageSize={} " + locationCode ,  p.getPageNumber(), p.getPageSize());
    	List<PowerPlanet> powerPlanet = repository.findByStateLocationCode(locationCode,p);
         
        if(!powerPlanet.isEmpty()) {
            return powerPlanet;
        } else {
            throw new RecordNotFoundException("No power planet record exist for given locationCode");
        }
    }
    
    public List<PowerPlanet> getPowerPlanetByLocationCode(String locationCode) throws RecordNotFoundException
    {
    	logger.info("locationCode= {} " + locationCode );
    	List<PowerPlanet> powerPlanet = repository.findByStateLocationCode(locationCode);
         
        if(!powerPlanet.isEmpty()) {
            return powerPlanet;
        } else {
            throw new RecordNotFoundException("No power planet record exist for given locationCode");
        }
    }
    
    public Map<String, String> getGenerationAmount(String locationCode) throws ArithmeticException, RecordNotFoundException {
    	logger.info("locationCode= {} " + locationCode );
    	double totalGeneratedAmt = 0;
    	double totalGeneratedAmtByLocationCode = 0;
    	try {
    		totalGeneratedAmt = repository.getTotalGeneration();
        	totalGeneratedAmtByLocationCode = repository.getTotalGenerationByLocationCode(locationCode);
    	}catch(Exception e) {
    		throw new RecordNotFoundException("No power planet record exist for given locationCode");
    	}
    	Map<String, String> results = new HashMap<String,String>();
    	try {
    		results.put("Generated Power",Double.toString(totalGeneratedAmtByLocationCode));
    		results.put("Total Generated Amt",Double.toString(totalGeneratedAmt));
    		results.put("Generation Percentage",Double.toString(( (totalGeneratedAmtByLocationCode/totalGeneratedAmt) * 100) ));
    	}catch(ArithmeticException e) {
    		throw new ArithmeticException("ArithmeticException : " + e.getMessage());
    	}
    	return results;
    }
    
}