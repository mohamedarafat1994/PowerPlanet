package com.thecollective.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="POWERPLANET")
public class PowerPlanet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long SEQGEN19;
    
    @Column(name="YEAR")
    private String year;
    
    @Column(name="PSTATABB")
    private String stateLocationCode;
    
    @Column(name="PNAME")
    private String name;
    
    @Column(name="GENID")
    private String generatorId;
    
    @Column(name="GENSTAT")
    private String generatorStatus;
    
    @Column(name="GENNTAN")
    private double generatorAnnualNetGeneration;
    
    
    public Long getId() {
		return SEQGEN19;
	}
	public void setId(Long id) {
		this.SEQGEN19 = id;
	}

	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}

	public String getStateLocationCode() {
		return stateLocationCode;
	}
	public void setStateLocationCode(String stateLocationCode) {
		this.stateLocationCode = stateLocationCode;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getGeneratorId() {
		return generatorId;
	}
	public void setGeneratorId(String generatorId) {
		this.generatorId = generatorId;
	}
	
	public String getGeneratorStatus() {
		return generatorStatus;
	}
	public void setGeneratorStatus(String generatorStatus) {
		this.generatorStatus = generatorStatus;
	}
	
	public double getGeneratorAnnualNetGeneration() {
		return generatorAnnualNetGeneration;
	}
	public void setGeneratorAnnualNetGeneration(double generatorAnnualNetGeneration) {
		this.generatorAnnualNetGeneration = generatorAnnualNetGeneration;
	}

	/*
    public String getRecord() {
        return "PowerPlanet [id=" + SEQGEN19 + ", name=" + name + 
                ", year=" + year + ", locationCode=" + stateLocationCode + 
                ", generatorId=" + generatorId + ", generatorStatus=" + generatorStatus + 
                ", generatorAnnualNetGeneration=" + generatorAnnualNetGeneration + "]";
    }
    
    */
}