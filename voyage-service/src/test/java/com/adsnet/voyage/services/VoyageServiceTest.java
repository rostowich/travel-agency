package com.adsnet.voyage.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.adsnet.voyage.entities.Voyage;
import com.adsnet.voyage.exceptions.ItemAlreadyExistException;
import com.adsnet.voyage.repositories.IVoyageRepository;


@SpringBootTest
@RunWith(SpringRunner.class)
public class VoyageServiceTest {
	
	@InjectMocks
	private VoyageService voyageService;
	
	@Mock
	private IVoyageRepository voyageRepositoryMock;
	
	@Mock
	private RestTemplate restTemplateMock;
	
	@Mock
	private IUrlService urlServiceMock;
	
	private Voyage voyage;
	
	private List<Voyage> listOfVoyage;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		
		voyage=new Voyage();
		voyage.setArchived(false);
		voyage.setDepartureDate(Date.valueOf("2018-01-01"));
		voyage.setDepartureHourId(1L);
		voyage.setNumberOfPlace(30);
		voyage.setPathId("1_6");
		
		listOfVoyage=new ArrayList<>();
		listOfVoyage.add(new Voyage(Date.valueOf("2018-01-01"), 30, 0, false, 1L, null, "2_3"));
		listOfVoyage.add(new Voyage(Date.valueOf("2018-01-01"), 30, 0, false, 2L, null, "2_2"));
		listOfVoyage.add(new Voyage(Date.valueOf("2018-01-01"), 30, 0, false, 3L, null, "1_3"));
	}
	
	@Test
	public void testSaveVoyageThrowItemAlreadyExistException(){
		
		when(voyageRepositoryMock.findById("2018-01-01_1_1_6")).thenReturn(Optional.of(voyage));
		try{
			voyageService.save(voyage);
		}
		catch (Exception e) {
			assertThat(e)
			.isInstanceOf(ItemAlreadyExistException.class)
            .hasMessage("Ce voyage existe déjà dans le système");
		}
	}
	
	@Test
	public void testSaveVoyage() throws Exception{
		//Mocking of dependencies
		when(voyageRepositoryMock.findById("2018-01-01_1_1_6")).thenReturn(Optional.empty());
		when(voyageRepositoryMock.save(refEq(voyage, "id"))).thenReturn(voyage);
		
		//Call the concrete method
		Voyage voyageSaved=voyageService.save(voyage);
		verify(voyageRepositoryMock, times(1)).findById("2018-01-01_1_1_6");
		verify(voyageRepositoryMock, times(1)).save(refEq(voyage, "id"));
		assertThat(voyageSaved.getId()).isEqualTo("2018-01-01_1_1_6");
		assertThat(voyageSaved.getDepartureHourId()).isEqualTo(1L);
		assertThat(voyageSaved.getPathId()).isEqualTo("1_6");
		assertThat(voyageSaved.getNumberOfPlace()).isEqualTo(30);
		assertThat(voyageSaved.getDepartureDate()).isEqualTo(Date.valueOf("2018-01-01"));
	}
	
	@Test
	public void testUpdate(){
		//Mocking the dependencies
		when(voyageRepositoryMock.save(voyage)).thenReturn(voyage);
		//testing of the concrete method
		Voyage voyageUpdated=voyageService.update(voyage);
		
		verify(voyageRepositoryMock, times(1)).save(voyage);
		assertThat(voyageUpdated.getDepartureHourId()).isEqualTo(1L);
		assertThat(voyageUpdated.getPathId()).isEqualTo("1_6");
		assertThat(voyageUpdated.getNumberOfPlace()).isEqualTo(30);
		assertThat(voyageUpdated.getDepartureDate()).isEqualTo(Date.valueOf("2018-01-01"));
	}
	
	
	
	@Test
	public void testFindVoyageById(){
		//Mocking the dependency
		when(voyageRepositoryMock.findById(anyString())).thenReturn(Optional.of(voyage));
		//Testing the concrete method
		Optional<Voyage> voyageFound=voyageService.findVoyageById("1");
		
		verify(voyageRepositoryMock, times(1)).findById(anyString());
		assertThat(voyageFound.get().getDepartureHourId()).isEqualTo(1L);
		assertThat(voyageFound.get().getPathId()).isEqualTo("1_6");
		assertThat(voyageFound.get().getNumberOfPlace()).isEqualTo(30);
		assertThat(voyageFound.get().getDepartureDate()).isEqualTo(Date.valueOf("2018-01-01"));
		
	}

	@Test
	public void testDeleteVoyage(){
		//Mocking the dependency
		doNothing().when(voyageRepositoryMock).delete(anyString());
		//Testing the method
		voyageService.delete("1");
		
		verify(voyageRepositoryMock, times(1)).delete("1");
	}
	
	@Test
	public void testFindAll(){
		//Mocking the dependency
		when(voyageRepositoryMock.findAll()).thenReturn(listOfVoyage);
		//Testing the concrete method
		List<Voyage> voyageList=voyageService.findAll();
		
		verify(voyageRepositoryMock, times(1)).findAll();
		assertThat(voyageList.size()).isEqualTo(3);
		assertThat(voyageList.get(0).getDepartureDate()).isEqualTo(Date.valueOf("2018-01-01"));
		assertThat(voyageList.get(0).getNumberOfPlace()).isEqualTo(30);
		assertThat(voyageList.get(0).getArchived()).isEqualTo(false);
		assertThat(voyageList.get(0).getDepartureHourId()).isEqualTo(1);
		assertThat(voyageList.get(0).getPathId()).isEqualTo("2_3");
		
		assertThat(voyageList.get(1).getDepartureDate()).isEqualTo(Date.valueOf("2018-01-01"));
		assertThat(voyageList.get(1).getNumberOfPlace()).isEqualTo(30);
		assertThat(voyageList.get(1).getArchived()).isEqualTo(false);
		assertThat(voyageList.get(1).getDepartureHourId()).isEqualTo(2);
		assertThat(voyageList.get(1).getPathId()).isEqualTo("2_2");
		
		assertThat(voyageList.get(2).getDepartureDate()).isEqualTo(Date.valueOf("2018-01-01"));
		assertThat(voyageList.get(2).getNumberOfPlace()).isEqualTo(30);
		assertThat(voyageList.get(2).getArchived()).isEqualTo(false);
		assertThat(voyageList.get(2).getDepartureHourId()).isEqualTo(3);
		assertThat(voyageList.get(2).getPathId()).isEqualTo("1_3");
		
	}
}
