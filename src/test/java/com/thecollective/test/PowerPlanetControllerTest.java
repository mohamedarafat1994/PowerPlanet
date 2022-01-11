package com.thecollective.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.thecollective.model.PowerPlanet;

public class PowerPlanetControllerTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void getAllPowerPlanets() throws Exception {
		String uri = "/powerplanet";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		PowerPlanet[] powerPlanetlist = super.mapFromJson(content, PowerPlanet[].class);
		assertTrue(powerPlanetlist.length > 0);
	}

	@Test
	public void getPowerPlanetById() throws Exception {
		String uri = "/powerplanet/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		PowerPlanet powerPlanet = super.mapFromJson(content, PowerPlanet.class);
		assertTrue(powerPlanet.getId() == 1);
	}
	
	@Test
	public void getPowerPlanetByLocationCodeWithoutPaggination() throws Exception {
		String uri = "/powerplanet/locationcode?code=AK";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		PowerPlanet[] powerPlanetlist = super.mapFromJson(content, PowerPlanet[].class);
		assertTrue(powerPlanetlist.length > 0);
	}
	
	@Test
	public void getPowerPlanetByLocationCodeWithPaggination() throws Exception {
		String uri = "/powerplanet/locationcode?code=AK&page=1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		PowerPlanet[] powerPlanetlist = super.mapFromJson(content, PowerPlanet[].class);
		assertTrue(powerPlanetlist.length <= 10);
	}
	
	@Test
	public void getPowerPlanetByLocationCodeWithoutLocationCodeAndWithoutPaggination() throws Exception {
		String uri = "/powerplanet/locationcode?code=";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		PowerPlanet[] powerPlanetlist = super.mapFromJson(content, PowerPlanet[].class);
		assertTrue(powerPlanetlist.length > 0);
	}
	
	@Test
	public void getAllPowerPlanetsWithPaggination() throws Exception {
		String uri = "/powerplanet/locationcode?code=&page=2";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		PowerPlanet[] powerPlanetlist = super.mapFromJson(content, PowerPlanet[].class);
		assertTrue(powerPlanetlist.length <= 10);
	}
	
	@Test
	public void getPlanetGenerationDataByLocationCode() throws Exception {
		String uri = "/powerplanet/getgenerationbylocation?code=AK";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		String result = super.mapToJson(content);
		assertTrue(result.contains("Generated Power"));
	}
}
