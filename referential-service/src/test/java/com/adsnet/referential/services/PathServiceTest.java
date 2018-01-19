package com.adsnet.referential.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.refEq;
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
import com.adsnet.referential.entities.Path;
import com.adsnet.referential.exceptions.ItemAlreadyExistException;
import com.adsnet.referential.repositories.IPathRepository;


@SpringBootTest
@RunWith(SpringRunner.class)
public class PathServiceTest {

	@InjectMocks
	private PathService pathService;
	
	@Mock
	private IPathRepository pathRepository;
	
	private Path path;
	
	private List<Path> listOfPath;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		City departure=new City();
		departure.setLabel("DOUALA");
		City destination=new City();
		destination.setLabel("YAOUNDE");
		path=new Path("TEST", null, departure, destination);
		
		listOfPath=new ArrayList<>();
		listOfPath.add(new Path("1", null, new City(1L, "Douala"), new City(2L, "Yaounde")));
		listOfPath.add(new Path("2", null, new City(2L, "Yaounde"), new City(1L, "Douala")));
		listOfPath.add(new Path("3", null, new City(3L, "Bafoussam"), new City(2L, "Yaounde")));
	}
	
	@Test
	public void testCreatePathThrowsItemAlreadyExistException(){
		
		when(pathRepository.findById("TEST")).thenReturn(Optional.of(path));
		try{
			pathService.save(path);
		}
		catch(Exception e){
			assertThat(e)
			.isInstanceOf(ItemAlreadyExistException.class)
            .hasMessage("Ce trajet existe déjà dans le système");
		}	
	}
	
	@Test
	public void testCreatePath() throws ItemAlreadyExistException{
		
		when(pathRepository.findById("TEST")).thenReturn(Optional.empty());
		when(pathRepository.save(refEq(path))).thenReturn(path);
		
		Path pathSaved=pathService.save(path);
		
		verify(pathRepository, times(1)).findById("TEST");
		verify(pathRepository, times(1)).save(path);
		assertThat(pathSaved.getId()).isEqualTo("TEST");
		assertThat(pathSaved.getLabel()).isEqualTo("DOUALA - YAOUNDE");
		assertThat(pathSaved.getDeparture().getLabel()).isEqualTo("DOUALA");
		assertThat(pathSaved.getDestination().getLabel()).isEqualTo("YAOUNDE");	
	}
	
	@Test
	public void testFindAll(){
		
		//Mocking the findAll() method of PathRepository
		when(pathRepository.findAll()).thenReturn(listOfPath);
		
		//Then i can call the real method to test
		List<Path> pathList=pathRepository.findAll();
		
		verify(pathRepository, times(1)).findAll();
		assertThat(pathList.size()).isEqualTo(3);
		assertThat(pathList.get(0).getId()).isEqualTo("1");
		assertThat(pathList.get(1).getId()).isEqualTo("2");
		assertThat(pathList.get(2).getId()).isEqualTo("3");
	}
	
	@Test
	public void testDelete(){	
		doNothing().when(pathRepository).delete(anyString());	
		pathService.delete("1");
		
		verify(pathRepository, times(1)).delete("1");
	}
	
	@Test
	public void testFindAllByExample(){
		
		Page<Path> pageOfPath=new PageImpl<>(listOfPath);
		
		Path pathExample=new Path();
		path.setId("1");
		path.setDeparture(new City(1L, "Douala"));
		path.setDestination(new City(2L, "Yaounde"));
		
		//Mocking the method of the pathRepository interface
		when(pathRepository.findAll(anyObject(),any(Pageable.class))).thenReturn(pageOfPath);
					
		//Now we can test the concrete method
		Page<Path> pagePath=pathService.findAllByExample(pathExample, new PageRequest(0, 3));
		verify(pathRepository, times(1)).findAll(anyObject(),any(Pageable.class));
		assertThat(pagePath.getTotalElements()).isEqualTo(3);
		assertThat(pagePath.getNumberOfElements()).isEqualTo(3);
		assertThat(pagePath.getContent().size()).isEqualTo(3);
	}
	
	@Test
	public void testFindByPath(){
		Path pathSearch=new Path("1", null, new City(1L, "Douala"), new City(2L, "Yaounde"));
		//Mocking of the findById method of pathRepository interface
		when(pathRepository.findById("1")).thenReturn(Optional.of(pathSearch));
		
		//Then we can test the method
		Optional<Path> pathFound=pathService.findPathById("1");
		
		verify(pathRepository, times(1)).findById("1");
		assertThat(pathFound.get().getId()).isEqualTo("1");
		assertThat(pathFound.get().getDeparture().getLabel()).isEqualTo("Douala");
		assertThat(pathFound.get().getDestination().getLabel()).isEqualTo("Yaounde");
	}
}
