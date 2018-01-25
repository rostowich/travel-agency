package com.adsnet.voyage.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.adsnet.voyage.entities.Voyage;
import com.adsnet.voyage.exceptions.ItemAlreadyExistException;
import com.adsnet.voyage.objects.Bus;
import com.adsnet.voyage.objects.DepartureHour;
import com.adsnet.voyage.objects.Path;
import com.adsnet.voyage.repositories.IVoyageRepository;
import com.adsnet.voyage.repositories.VoyageSpecification;


@Service
public class VoyageService implements IVoyageService{
	
	@Autowired
	private IVoyageRepository voyageRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private IUrlService urlService;

	@Override
	public Voyage save(Voyage voyage) throws ItemAlreadyExistException {
		// TODO Auto-generated method stub
		voyage=initializeVoyage(voyage);
		Optional<Voyage> voyageSearch=voyageRepository.findById(voyage.getId());
		if(voyageSearch.isPresent())
			throw new ItemAlreadyExistException("Ce voyage existe déjà dans le système");
		
		return voyageRepository.save(voyage);
	}

	@Override
	public Voyage update(Voyage voyage) {
		// TODO Auto-generated method stub
		return voyageRepository.save(voyage);
	}

	@Override
	public Optional<Voyage> findVoyageById(String id) {
		// TODO Auto-generated method stub
		Optional<Voyage> voyageSearched= voyageRepository.findById(id);
		
		//get the Bus, DepartureHour and Path from the referential-service
		/*DepartureHour departureHour=null;
		Path path=null;
		Bus bus=null;
		if(voyageSearched.isPresent()){
			if(voyageSearched.get().getDepartureHourId()!=null){
				//Get the departureHour
				departureHour=restTemplate.getForObject(urlService.getDepartureHourServiceUrl(Long.toString(voyageSearched.get().getDepartureHourId())),
								DepartureHour.class);
			}
			if(voyageSearched.get().getPathId()!=null){
				//Get the Path 
				path=restTemplate.getForObject(urlService.getPathServiceUrl(voyageSearched.get().getPathId()), Path.class);
			}
			if(voyageSearched.get().getBusId()!=null){
				bus=restTemplate.getForObject(urlService.getBusServiceUrl(Long.toString(voyageSearched.get().getBusId())), Bus.class);
			}
		}
		
		voyageSearched.get().setDepartureHour(departureHour);
		voyageSearched.get().setBus(bus);
		voyageSearched.get().setPath(path);*/
		
		return voyageSearched;		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		voyageRepository.delete(id);
	}

	@Override
	public List<Voyage> findAll() {
		// TODO Auto-generated method stub
		return voyageRepository.findAll();
	}

	@Override
	public Page<Voyage> findAllByExample(Voyage voyage, Pageable pageable) {
		// TODO Auto-generated method stub
		Specification<Voyage> specification=new VoyageSpecification(voyage);
		return voyageRepository.findAll(specification, pageable);
	}
	
	/**
	 * This method is used to set attributes of the Voyage entity before saving it
	 * @param voyage
	 * @return
	 */
	private static Voyage initializeVoyage(Voyage voyage){
		
		voyage.setId(voyage.getDepartureDate()+"_"+voyage.getDepartureHourId()+"_"+voyage.getPathId());
		voyage.setArchived(new Boolean(false));
		voyage.setNumberOfSalePlace(0);
		
		return voyage;
	}

}
