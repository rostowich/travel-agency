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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.adsnet.referential.entities.Classe;
import com.adsnet.referential.entities.Path;
import com.adsnet.referential.repositories.IClassRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClassServiceTest {

	
	@InjectMocks
	private ClassService classService;
	
	@Mock
	private IClassRepository classRepository;
		
	private Classe classe;
	
	private List<Classe> listOfClasse;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		
		classe=new Classe();
		classe.setId("1");
		classe.setLabel("PRESTIGE");
		
		listOfClasse=new ArrayList<>();
		listOfClasse.add(new Classe("1", "PRESTIGE", null, null));
		listOfClasse.add(new Classe("2", "LOW COST", null, null));
		listOfClasse.add(new Classe("3", "CLASSIC", null, null));
	}
	
	@Test
	public void testSaveClass(){
		//Mocking of save method of ClassRepository interface
		when(classRepository.save(classe)).thenReturn(classe);
		
		//Then we can test the real method
		Classe classeSaved=classService.save(classe);
		
		verify(classRepository, times(1)).save(classe);
		assertThat(classeSaved.getId()).isEqualTo("1");
		assertThat(classeSaved.getLabel()).isEqualTo("PRESTIGE");	
	}

	@Test
	public void testFindById(){
		
		//Mocking the findById of the ClassRepository interface
		when(classRepository.findById("1")).thenReturn(Optional.of(classe));
		
		//Then we can test the concrete method of the ClassService
		Optional<Classe> classFound=classService.findById("1");
		
		verify(classRepository, times(1)).findById("1");
		assertThat(classFound.get().getId()).isEqualTo("1");
		assertThat(classFound.get().getLabel()).isEqualTo("PRESTIGE");
	}
	
	@Test
	public void testFindAll(){
		
		//Mocking of findAll() method of ClassRepository
		when(classRepository.findAll()).thenReturn(listOfClasse);
		
		//Then we can test the method of classService
		List<Classe> classList=classService.findAll();
		
		verify(classRepository, times(1)).findAll();
		assertThat(classList.size()).isEqualTo(3);
		assertThat(classList.get(0).getLabel()).isEqualTo("PRESTIGE");
		assertThat(classList.get(1).getLabel()).isEqualTo("LOW COST");
		assertThat(classList.get(2).getLabel()).isEqualTo("CLASSIC");		
	}
	
	
	@Test
	public void testDelete(){
		
		//Mocking the delete method of the ClassRepository interface
		doNothing().when(classRepository).delete("1");
		
		classService.delete("1");
		verify(classRepository, times(1)).delete("1");		
	}
	
	@Test
	public void testFindAllByExample(){
		
		Page<Classe> pageOfClasse=new PageImpl<>(listOfClasse);
		
	    Classe classExample=new Classe("1", "PRESTIGE", null, new Path("1",null, null, null));
	    
	    //Mocking the findAll method of the ClassRepository interface
	    when(classRepository.findAll(anyObject(),any(Pageable.class))).thenReturn(pageOfClasse);
	    
	    //now we can call the concrete method
	    Page<Classe> pageFound=classService.findAllByExample(classExample, new PageRequest(0, 3));
	    
	    verify(classRepository, times(1)).findAll(anyObject(),any(Pageable.class));
	    assertThat(pageFound.getTotalElements()).isEqualTo(3);
		assertThat(pageFound.getNumberOfElements()).isEqualTo(3);
		assertThat(pageFound.getContent().size()).isEqualTo(3);		
	}
}
