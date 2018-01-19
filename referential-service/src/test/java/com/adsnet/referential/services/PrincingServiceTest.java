package com.adsnet.referential.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
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
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.adsnet.referential.entities.City;
import com.adsnet.referential.entities.Classe;
import com.adsnet.referential.entities.Pricing;
import com.adsnet.referential.enums.TravelType;
import com.adsnet.referential.exceptions.ItemAlreadyExistException;
import com.adsnet.referential.repositories.IPricingRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PrincingServiceTest {

	@InjectMocks
	private PricingService pricingService;
	
	@Mock
	private IPricingRepository pricingRepository;
	
	private Pricing pricing;
	
	private List<Pricing> listOfPricing;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		
		pricing=new Pricing("1", TravelType.ONE_WAY_TRAVEL, "6000", new Classe("1", "PRESTIGE", null, null));
		
		listOfPricing=new ArrayList<>();
		listOfPricing.add(new Pricing("1", TravelType.ONE_WAY_TRAVEL, "6000", new Classe("1", "CLASSIC", null, null)));
		listOfPricing.add(new Pricing("2", TravelType.ONE_WAY_TRAVEL, "10000", new Classe("1", "PRESTIGE", null, null)));
		listOfPricing.add(new Pricing("3", TravelType.TWO_WAY_TRAVEL, "19000", new Classe("1", "PRESTIGE", null, null)));
	}
	
	@Test
	public void testSaveThrowsItemAlreadyExistException(){
		
		//Mocking the findById() method of the pricingRepository interface
		when(pricingRepository.findById("1")).thenReturn(Optional.of(pricing));
		
		//Then we can test the concrete method
		try{
		pricingService.save(pricing);
		}
		catch(Exception e){
			assertThat(e)
			.isInstanceOf(ItemAlreadyExistException.class)
            .hasMessage("Cette tarification existe deja");
		}	
	}
	
	@Test
	public void testSave() throws ItemAlreadyExistException{
		
		//Mocking the findById() and save() method of the pricingRepository interface
		when(pricingRepository.findById("1")).thenReturn(Optional.empty());
		when(pricingRepository.save(pricing)).thenReturn(pricing);
		
		//Then we can test the concrete method
		Pricing pricingSaved=pricingService.save(pricing);
		
		//Assertions
		verify(pricingRepository, times(1)).findById("1");
		verify(pricingRepository, times(1)).save(pricing);
		assertThat(pricingSaved).isNotNull();
		assertThat(pricingSaved.getId()).isEqualTo("1");
		assertThat(pricingSaved.getTravelType().getName()).isEqualTo("Aller-simple");
		assertThat(pricingSaved.getAmount()).isEqualTo("6000");
		assertThat(pricingSaved.getClasse().getLabel()).isEqualToIgnoringCase("PRESTIGE");
	}
	
	@Test
	public void testDelete(){
		//Mocking the delete() method of the PricingRepository
		doNothing().when(pricingRepository).delete("1");
		
		//Then we call the concrete method of the PricingService
		pricingService.delete("1");
		verify(pricingRepository, times(1)).delete("1");
	}
	
	@Test
	public void testFindAll(){
		
		//Mocking the findAll() method of PricingRepository interface
		when(pricingRepository.findAll()).thenReturn(listOfPricing);
		
		//calling of the concrete method
		List<Pricing> pricingList=pricingService.findAll();
		
		verify(pricingRepository, times(1)).findAll();
		assertThat(pricingList.size()).isEqualTo(3);
		assertThat(pricingList.get(0).getId()).isEqualTo("1");
		assertThat(pricingList.get(1).getId()).isEqualTo("2");
		assertThat(pricingList.get(2).getId()).isEqualTo("3");
	}
	
	@Test
	public void testFindAllByExample(){
		Page<Pricing> pageOfPricing=new PageImpl<>(listOfPricing);
		
		Pricing pricingExample=new Pricing();
		pricingExample.setClasse(new Classe("1", null, null, null));
		
		//Mocking the method of the pricingRepository interface
		when(pricingRepository.findAll(anyObject(),any(Pageable.class))).thenReturn(pageOfPricing);
					
		//Now we can test the concrete method
		Page<Pricing> pagePricing=pricingService.findAllByExample(pricingExample, new PageRequest(0, 3));
		assertThat(pagePricing.getTotalElements()).isEqualTo(3);
		assertThat(pagePricing.getNumberOfElements()).isEqualTo(3);
		assertThat(pagePricing.getContent().size()).isEqualTo(3);
		assertThat(pagePricing.getTotalPages()).isEqualTo(1);
		assertThat(pagePricing.getContent().get(0).getId()).isEqualTo("1");
		assertThat(pagePricing.getContent().get(1).getId()).isEqualTo("2");
		assertThat(pagePricing.getContent().get(2).getId()).isEqualTo("3");
		
		verify(pricingRepository, times(1)).findAll(anyObject(),any(Pageable.class));
	}
	
	@Test
	public void testUpdate(){
		
		//Mocking the save() method of the PricingRepository interface
		when(pricingRepository.save(pricing)).thenReturn(pricing);
		
		//calling the concrete method
		Pricing pricingUpdated=pricingService.update(pricing);
		
		verify(pricingRepository, times(1)).save(pricing);
		assertThat(pricingUpdated.getId()).isEqualTo("1");
		assertThat(pricingUpdated.getAmount()).isEqualTo("6000");
		assertThat(pricingUpdated.getTravelType().getName()).isEqualTo("Aller-simple");
		assertThat(pricingUpdated.getClasse().getLabel()).isEqualToIgnoringCase("PRESTIGE");
	}
	
	@Test
	public void testFindPricingById(){
		//Mocking the findById() method of the PricingRepository interface
		when(pricingRepository.findById("1")).thenReturn(Optional.of(pricing));
		
		//test the findPricingById() of the PricingService
		Optional<Pricing> pricingFound=pricingService.findPricingById("1");
		
		verify(pricingRepository, times(1)).findById("1");
		assertThat(pricingFound.get().getId()).isEqualTo("1");
		assertThat(pricingFound.get().getAmount()).isEqualTo("6000");
		assertThat(pricingFound.get().getClasse().getLabel()).isEqualToIgnoringCase("PRESTIGE");
	} 
	
	
}
