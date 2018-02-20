package com.maviance.protecting_queen.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maviance.protecting_queen.domain.User;
import com.maviance.protecting_queen.domain.UserRegistration;
import com.maviance.protecting_queen.exceptions.UserAlreadyExistException;
import com.maviance.protecting_queen.services.SignUpService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=SignUpController.class, secure=false)
public class SignUpControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	//We  have to add all these mocks to the spring ApplicationContext
	@MockBean
	private SignUpService signUpService;
	
	@Test
	public void SignUpTestWithInvalidParameters() throws Exception{
		
	    //test the method with empty username
		UserRegistration userWithEmptyUsernameAndPassword=new UserRegistration("", "", "");
		RequestBuilder requestBuilderEmptyUsername = MockMvcRequestBuilders
				.post("/signup")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsBytes(userWithEmptyUsernameAndPassword))
				.contentType(MediaType.APPLICATION_JSON);
		String ExpectedResponseWithEmptyUsername="{\"message\":\"username required\",\"type\":\"ERROR\"}";
		
		MvcResult resultEmptyUsername = mockMvc.perform(requestBuilderEmptyUsername).andReturn();
		MockHttpServletResponse responseEmptyUsername=resultEmptyUsername.getResponse();
		assertThat(responseEmptyUsername.getContentAsString()).isEqualTo(ExpectedResponseWithEmptyUsername);
		
		//test the method with empty password
		UserRegistration userRegistrationWithEmptyPassword=new UserRegistration("maviance", "", "");
		RequestBuilder requestBuilderEmptypassword = MockMvcRequestBuilders
				.post("/signup")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsBytes(userRegistrationWithEmptyPassword))
				.contentType(MediaType.APPLICATION_JSON);
		String ExpectedResponseEmptyPassword="{\"message\":\"Password required\",\"type\":\"ERROR\"}";
		
		MvcResult resultEmptyPassword = mockMvc.perform(requestBuilderEmptypassword).andReturn();
		MockHttpServletResponse responseEmptyPassword=resultEmptyPassword.getResponse();
		assertThat(responseEmptyPassword.getContentAsString()).isEqualTo(ExpectedResponseEmptyPassword);

		//test the method with passwords not matching
		UserRegistration userRegistrationWithPasswordDoesNotMatch=new UserRegistration("maviance", "password", "password123");
		RequestBuilder requestBuilderPasswordDoesNotMatch = MockMvcRequestBuilders
				.post("/signup")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsBytes(userRegistrationWithPasswordDoesNotMatch))
				.contentType(MediaType.APPLICATION_JSON);
		String ExpectedResponsePasswordDoNotMatch="{\"message\":\"The passwords do not match\",\"type\":\"ERROR\"}";
		
		MvcResult resultPasswordDoNotMatch = mockMvc.perform(requestBuilderPasswordDoesNotMatch).andReturn();
		MockHttpServletResponse responseDoNotMatch=resultPasswordDoNotMatch.getResponse();
		assertThat(responseDoNotMatch.getContentAsString()).isEqualTo(ExpectedResponsePasswordDoNotMatch);
	}
	
	@Test
	public void registerTestInWhichTheUserAlreadyExists() throws Exception{
		
		User userExist=new User("maviance", "password");
		
		//Mock the register method of UserService
		when(signUpService.signUpUser(refEq(userExist))).thenThrow(new UserAlreadyExistException("This User already exist"));
		try{
			//Now we can test the register method
			UserRegistration registrationWithUserAlreadyExists=new UserRegistration("maviance", "password", "password");
			RequestBuilder requestBuilderWithUserAlreadyExists = MockMvcRequestBuilders
					.post("/signup")
					.accept(MediaType.APPLICATION_JSON_UTF8)
					.content(objectMapper.writeValueAsBytes(registrationWithUserAlreadyExists))
					.contentType(MediaType.APPLICATION_JSON);
			
			mockMvc.perform(requestBuilderWithUserAlreadyExists).andReturn();
		}
		catch(Exception e){
			assertThat(e)
			.isInstanceOf(UserAlreadyExistException.class)
			.hasMessage("This User already exists");
		}
	}
	
	@Test
	public void registerTestWithUserCreated() throws Exception{
				
		User user=new User("maviance", "password");
		
		//Mock the signUpUser method of SignUpService
		when(signUpService.signUpUser(refEq(user))).thenReturn(user);
		
		//Now we can test the register method
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/signup")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"username\": \"maviance\",\"password\": \"password\",\"confirmPassword\": \"password\"}")
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder)
				.andExpect(status().isCreated());
	}

}
