package com.adsnet.referential.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.adsnet.referential.entities.City;
import com.adsnet.referential.exceptions.ItemAlreadyExistException;
import com.adsnet.referential.repositories.ICityRepository;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CityServiceTest {
		
		@InjectMocks
		private CityService cityService;
		
		@Mock
		private ICityRepository cityRepository;
		
		private City city;
		
		private List<City> listOfCity;
		
		@Before
		public void setup(){
			MockitoAnnotations.initMocks(this);
			
			city=new City();
			city.setId(1L);
			city.setLabel("TEST");
			
			listOfCity=new ArrayList<>();
			listOfCity.add(new City(1L, "DOUALA"));
			listOfCity.add(new City(3L, "NHUDOUAL"));
			listOfCity.add(new City(4L, "KABDOUAL"));
		}
		
		@Test
		public void testSaveCityWithItemAlreadyExistException(){
			when(cityRepository.findByLabel("TEST")).thenReturn(Optional.of(city));
			try{
				cityService.save(city);
			}
			catch(Exception e){
				assertThat(e)
				.isInstanceOf(ItemAlreadyExistException.class)
	            .hasMessage("Cette ville existe dejà");
			}			
		}
		
		@Test
		public void testSaveCity() throws ItemAlreadyExistException{			
			when(cityRepository.findByLabel("TEST")).thenReturn(Optional.empty());
			when(cityRepository.save(city)).thenReturn(city);
			City citySaved=cityService.save(city);
			assertThat(citySaved.getLabel()).isEqualTo("TEST");
			verify(cityRepository, times(1)).findByLabel("TEST");
			verify(cityRepository, times(1)).save(city);
		}
		
		@Test
		public void testDeleteCity(){			
			doNothing().when(cityRepository).delete(1L);	
			cityService.delete(1L);
			
			verify(cityRepository, times(1)).delete(1L);
			
		}
		
		@Test
		public void testFindAllByExample(){						
			
			Page<City> pageOfCity=new PageImpl<>(listOfCity);
			
			City cityExample=new City();
			cityExample.setLabel("DOUA");
			
			//Mocking the method of the cityRepository interface
			when(cityRepository.findAll(anyObject(),any(Pageable.class))).thenReturn(pageOfCity);
						
			//Now we can test the concrete method
			Page<City> pageCity=cityService.findAllByExample(cityExample, new PageRequest(0, 3));
			assertThat(pageCity.getTotalElements()).isEqualTo(3);
			assertThat(pageCity.getNumberOfElements()).isEqualTo(3);
			assertThat(pageCity.getContent().size()).isEqualTo(3);
			assertThat(pageCity.getTotalPages()).isEqualTo(1);
			assertThat(pageCity.getContent().get(0).getLabel()).isEqualTo("DOUALA");
			assertThat(pageCity.getContent().get(1).getLabel()).isEqualTo("NHUDOUAL");
			assertThat(pageCity.getContent().get(2).getLabel()).isEqualTo("KABDOUAL");
			
			verify(cityRepository, times(1)).findAll(anyObject(),any(Pageable.class));
		}
		
		@Test
		public void testFindAll(){
			
			when(cityRepository.findAll()).thenReturn(listOfCity);
			
			List<City> cityList=cityService.findAll();
			assertThat(cityList.size()).isEqualTo(3);
			assertThat(cityList.get(0).getLabel()).isEqualTo("DOUALA");
			assertThat(cityList.get(1).getLabel()).isEqualTo("NHUDOUAL");
			assertThat(cityList.get(2).getLabel()).isEqualTo("KABDOUAL");
			
			verify(cityRepository, times(1)).findAll();
		}
		
		@Test
		public void testFindByLabel(){
			//Mocking the findByLabel method of CityRepository interface
			when(cityRepository.findByLabel("TEST")).thenReturn(Optional.of(city));
			
			//Call the real findByLabel of the cityService
			Optional<City> cityFound=cityService.findByLabel("TEST");
			assertThat(cityFound.get().getLabel()).isEqualTo("TEST");
			
			verify(cityRepository, times(1)).findByLabel("TEST");
		}
		
		@Test
		public void testFindCityById(){
			
			//Mocking the findById Method of CityRepository
			when(cityRepository.findById(1L)).thenReturn(Optional.of(city));
			
			//Call the concrete method of the service
			Optional<City> cityFound=cityService.findCityById(1L);
			
			assertThat(cityFound.get().getId()).isEqualTo(1L);
			assertThat(cityFound.get().getLabel()).isEqualTo("TEST");
			
			verify(cityRepository, times(1)).findById(1L);
		}
		
}
