package com.thecollective.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thecollective.model.PowerPlanet;
 
@Repository
public interface PowerPlanetRepository
        extends JpaRepository<PowerPlanet, Long> {

	Page<PowerPlanet> findAll(Pageable p);
	
	List<PowerPlanet> findByStateLocationCode(String stateLocationCode,Pageable p);
	
	List<PowerPlanet> findByStateLocationCode(String stateLocationCode);
	
	@Query(value = "SELECT SUM(GENNTAN) FROM POWERPLANET", nativeQuery = true)
	double getTotalGeneration();
	
	@Query(value = "SELECT SUM(GENNTAN) FROM POWERPLANET WHERE PSTATABB = ?1", nativeQuery = true)
	double getTotalGenerationByLocationCode(String locationCode);
	
}
