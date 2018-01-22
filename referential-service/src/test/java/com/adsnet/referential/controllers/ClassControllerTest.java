package com.adsnet.referential.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import com.adsnet.referential.entities.Classe;
import com.adsnet.referential.services.IClassService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value=ClasseController.class, secure=false)
public class ClassControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	//We  have to add all these mocks to the spring ApplicationContext
	@MockBean
	private IClassService classService;
	
	@MockBean
	private Environment environment;

	
	@Test 
	public void testGetAllClasses() throws Exception{
		
		Classe class1=new Classe("1", "PRESTIGE", null, null);
		Classe class2=new Classe("2", "CLASSIC", null, null);
		
		List<Classe> listOfClasse=new ArrayList<>();
		listOfClasse.add(class1);
		listOfClasse.add(class2);
		
		//Mocking of dependences
		when(classService.findAll()).thenReturn(listOfClasse);
		
		//Calling of concrete methods
		mockMvc.perform(get("/class/all").contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].id", is("1")))
		.andExpect(jsonPath("$[1].id", is("2")))
		.andExpect(jsonPath("$[0].label", is("PRESTIGE")))
		.andExpect(jsonPath("$[1].label", is("CLASSIC")));
		
		verify(classService, times(1)).findAll();
		verifyNoMoreInteractions(classService);
	}
	
	
	@Test
	public void testViewAllClasses() throws Exception{
		Classe class1=new Classe("1", "PRESTIGE", null, null);
		Classe class2=new Classe("2", "CLASSIC", null, null);
		
		List<Classe> listOfClasse=new ArrayList<>();
		listOfClasse.add(class1);
		listOfClasse.add(class2);
		
		//Mocking of dependences
		when(classService.findAllByExample(any(Classe.class), any(Pageable.class))).thenReturn(new PageImpl<>(listOfClasse));
		
		//Calling of concrete methods
		mockMvc.perform(get("/class?label=&pathId=&page=0").contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.totalElements", is(2)))
		.andExpect(jsonPath("$.totalPages", is(1)))
		.andExpect(jsonPath("$.number", is(0)))
		.andExpect(jsonPath("$.content[0].id", is(1)))
		.andExpect(jsonPath("$.content[0].label", is("PRESTIGE")))
		.andExpect(jsonPath("$.content[1].id", is(2)))
		.andExpect(jsonPath("$.content[1].label", is("CLASSIC")));
	}
	
}
