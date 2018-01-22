package com.adsnet.voyage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adsnet.voyage.entities.FileTravel;



/**
 * This class represents the Repository of the Driver entity.
 * It allows to access some repository methods to deal with the data source
 * @author Rostow
 *
 */
@Repository
public interface IFileTravelRepository extends JpaRepository<FileTravel, String>{
	
}
