package com.adsnet.subscription.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adsnet.subscription.entities.Souscription;


@Repository
public interface ISouscriptionRepository extends JpaRepository<Souscription, Long>{
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<Souscription> findById(Long id);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<Souscription> findByLoginOrEmailOrCompleteNameAllIgnoreCase(String login, String email, String completeName);
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	Optional<Souscription> findByEmail(String email);

	/**
	 * 
	 * @param completeName
	 * @return
	 */
	Optional<Souscription> findByCompleteName(String completeName);
}
