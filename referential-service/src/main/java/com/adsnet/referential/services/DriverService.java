package com.adsnet.referential.services;



import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.adsnet.referential.entities.Driver;
import com.adsnet.referential.exceptions.ItemAlreadyExistException;
import com.adsnet.referential.repositories.DriverSpecification;
import com.adsnet.referential.repositories.IDriverRepository;



@Service
public class DriverService implements IDriverService {

	private static final Logger logger = LoggerFactory.getLogger(DriverService.class);

	@Autowired
	IDriverRepository driverRepository;
	
	
	@Override
	public Driver save(Driver driver) throws ItemAlreadyExistException {
		//We need to check if this driver already exists
		Optional<Driver> driverExist=driverRepository.findByDriverLicenceNumber(driver.getDriverLicenceNumber());
		if(driverExist.isPresent())
			throw new ItemAlreadyExistException("Ce chauffeur existe dejà");
		
		return driverRepository.save(driver);
	}
	
	@Override
	public Driver update(Driver driver) {
		// TODO Auto-generated method stub
		return driverRepository.save(driver);
	}

	@Override
	public void delete(Long id) {
		driverRepository.delete(id);
	}

	@Override
	public Page<Driver> findAllByExample(Driver driver, Pageable pageable) {
		// TODO Auto-generated method stub
		Specification<Driver> specification=new DriverSpecification(driver);
		return driverRepository.findAll(specification, pageable);
	}

	@Override
	public List<Driver> findAll() {
		// TODO Auto-generated method stub
		return driverRepository.findAll();
	}

	@Override
	public Optional<Driver> findDriverById(Long id) {
		// TODO Auto-generated method stub
		return driverRepository.findById(id);
	}
	
}
