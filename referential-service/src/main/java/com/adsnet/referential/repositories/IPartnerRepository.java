package com.adsnet.referential.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adsnet.referential.entities.Partner;

/**
 * This class represents the Repository of the Partner entity.
 * It allows to access some repository methods to deal with the data source
 * @author Rostow
 *
 */
@Repository
public interface IPartnerRepository extends JpaRepository<Partner, Long>{

	/**
	 * Get the partner by its ID
	 * @param id
	 * @return
	 */
	Optional<Partner> findById(Long id);
	
	/**
	 * Get the partner by its name
	 */
	List<Partner> findByLabelIgnoreCaseContaining(String label);
	
	/**
	 * Find a partner by its login and its password to allow it to connect
	 * @param login
	 * @param password
	 * @return
	 */
	Optional<Partner> findByLoginAndPassword(String login, String password);
}
