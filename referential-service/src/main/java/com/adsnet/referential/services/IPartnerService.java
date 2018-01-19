package com.adsnet.referential.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.adsnet.referential.entities.Partner;


/**
 * This interface exposes all the business operations available for the Partner entity
 * @author Rostow
 *
 */
@Component
public interface IPartnerService {

	/**
	 * This method saves the partner into the database
	 * @param partner
	 * @return
	 */
	public Partner save(Partner partner);
	
	/**
	 * This method find the partner by its ID
	 * @param id
	 * @return
	 */
	public Optional<Partner> findById (Long id);
	
	/**
	 * The method return all the partners found into the database
	 * @return
	 */
	public List<Partner> findAll();
	
	/**
	 * This method finds all the partners that contain the label in their name
	 * @param label
	 * @return
	 */
	public List<Partner> findAllCitiesContainingLabel(String label);
	
	/**
	 * Enable a partner to connect
	 * @param login
	 * @param password
	 * @return
	 */
	public Optional<Partner> ConnectAsPartner(String login, String password);
}
