package com.adsnet.referential.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.adsnet.referential.entities.City;
import com.adsnet.referential.exceptions.ItemAlreadyExistException;
import com.adsnet.referential.services.ICityService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@WebMvcTest(value=CityController.class, secure=false)
public class CityControlerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	//We  have to add all these mocks to the spring ApplicationContext
	@MockBean
	private ICityService cityService;
	
	@MockBean
	private Environment environment;
	
	
	@Test
	public void testViewAllCities() throws Exception{
		
		City city1=new City(1L, "Douala");
		City city2=new City(2L, "Yaounde");
		List<City> listOfCity=new ArrayList<>();
		listOfCity.add(city1);
		listOfCity.add(city2);
		
		City cityExample=new City(null, "ou");
		
		//Mocking of dependences
		when(environment.getProperty("pages.number")).thenReturn("2");
		when(cityService.findAllByExample(refEq(cityExample), refEq(new PageRequest(0, 2)))).thenReturn(new PageImpl<>(listOfCity));
		
		//Calling of concrete methods
		mockMvc.perform(get("/city?id=&label=ou&page=0").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.totalElements", is(2)))
		.andExpect(jsonPath("$.totalPages", is(1)))
		.andExpect(jsonPath("$.number", is(0)))
		.andExpect(jsonPath("$.content[0].id", is(1)))
		.andExpect(jsonPath("$.content[0].label", is("Douala")))
		.andExpect(jsonPath("$.content[1].id", is(2)))
		.andExpect(jsonPath("$.content[1].label", is("Yaounde")));
		
		verify(environment, times(1)).getProperty("pages.number");
		verify(cityService, times(1)).findAllByExample(refEq(cityExample), refEq(new PageRequest(0, 2)));
		verifyNoMoreInteractions(environment);
		verifyNoMoreInteractions(cityService);
	}
	
	@Test
	public void testAddCityThrowsItemAlreadyExistException() throws Exception{
		
		City city=new City();
		city.setLabel("Douala");
		RequestBuilder requestBuilder=MockMvcRequestBuilders
				.post("/city")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsBytes(city))
				.contentType(MediaType.APPLICATION_JSON);
		
		//We mock the dependence		
		when(cityService.save(city)).thenThrow(new ItemAlreadyExistException());
		try{
			//Now we can test the concrete method
			mockMvc.perform(requestBuilder).andReturn();
		}
		catch(Exception e){
			assertThat(e)
			.isInstanceOf(ItemAlreadyExistException.class)
			.hasMessage("Cet element existe dejà dans le système");
		}	
	}
	
	@Test
	public void testAddCity() throws Exception{
		
		City city=new City();
		city.setLabel("Douala");
		city.setId(1L);
		RequestBuilder requestBuilder=MockMvcRequestBuilders
				.post("/city")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsBytes(city))
				.contentType(MediaType.APPLICATION_JSON);
		
		//We mock the dependence		
		when(cityService.save(refEq(city))).thenReturn(city);
		//We can then call the concrete method
		mockMvc.perform(requestBuilder)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.label", is("Douala")));	
		
		verify(cityService, times(1)).save(refEq(city));
		verifyNoMoreInteractions(cityService);
	}
	
	
	
	@Test
	public void testDeleteCityReturnNotFound() throws Exception{
		
		RequestBuilder requestBuilder=MockMvcRequestBuilders
				.delete("/city/1")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON);
		
		//We mock the dependences		
		when(cityService.findCityById(1L)).thenReturn(Optional.empty());
		
		//We can then call the concrete method
		mockMvc.perform(requestBuilder)
		.andExpect(status().isNotFound());
		
		verify(cityService,timeout(1)).findCityById(1L);
		verifyNoMoreInteractions(cityService);
	}
	
	@Test
	public void testDeleteCity() throws Exception{
		City city=new City();
		city.setLabel("Douala");
		city.setId(1L);
		RequestBuilder requestBuilder=MockMvcRequestBuilders
				.delete("/city/1")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON);
		
		//We mock the dependence		
		when(cityService.findCityById(1L)).thenReturn(Optional.of(city));
		doNothing().when(cityService).delete(1L);
		
		//We can then call the concrete method
		mockMvc.perform(requestBuilder)
		.andExpect(status().isNoContent());
		
		verify(cityService, times(1)).findCityById(1L);
		verify(cityService, times(1)).delete(1L);
		verifyNoMoreInteractions(cityService);
	}
	
	@Test
	public void testUpdateCityReturnNotFound() throws Exception{
		
		City city=new City();
		city.setLabel("Douala");
		city.setId(1L);
		
		//We mock the dependence		
		when(cityService.findCityById(1L)).thenReturn(Optional.empty());
		//Testing the concrete method
		mockMvc.perform(put("/city")
		 .contentType(MediaType.APPLICATION_JSON)
         .content(objectMapper.writeValueAsBytes(city))
		 .contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
		
		verify(cityService, times(1)).findCityById(1L);
		verifyNoMoreInteractions(cityService);
	}
	
	@Test
	public void testUpdateCityReturnItemAlreadyExistException() throws Exception{
		
		City city=new City();
		city.setLabel("Douala");
		city.setId(1L);
		RequestBuilder requestBuilder=MockMvcRequestBuilders
				.put("/city")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsBytes(city))
				.contentType(MediaType.APPLICATION_JSON);
		
		//We mock the dependence		
		when(cityService.findCityById(1L)).thenReturn(Optional.of(city));
		when(cityService.save(refEq(city))).thenThrow(new ItemAlreadyExistException());
		
		//Testing the concrete method
		try{
			//Now we can test the concrete method
			mockMvc.perform(put("/city")
					 .contentType(MediaType.APPLICATION_JSON)
			         .content(objectMapper.writeValueAsBytes(city))
					 .contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		}
		catch(Exception e){
			assertThat(e)
			.isInstanceOf(ItemAlreadyExistException.class)
			.hasMessage("Cet element existe dejà dans le système");
		}	
		
	}
	
	@Test
	public void testUpdateCity() throws Exception{
		
		City city=new City();
		city.setLabel("Douala");
		city.setId(1L);

		//We mock the dependence		
		when(cityService.findCityById(1L)).thenReturn(Optional.of(city));
		when(cityService.save(refEq(city))).thenReturn(city);
		
		//We can then call the concrete method
		mockMvc.perform(put("/city")
				 .contentType(MediaType.APPLICATION_JSON)
		         .content(objectMapper.writeValueAsBytes(city))
				 .contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.label", is("Douala")));	
		
		verify(cityService, times(1)).findCityById(1L);
		verify(cityService, times(1)).save(refEq(city));
		verifyNoMoreInteractions(cityService);
	}
	
}
