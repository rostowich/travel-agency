package com.adsnet.referential.repositories;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.adsnet.referential.entities.Driver;

/**
 * This class is used to search Drivers specifying the criteria
 *
 */
public class DriverSpecification implements Specification<Driver>{

	private Driver filter;
	
	public DriverSpecification(Driver filter) {
		super();
		this.filter=filter;
	}
	 
	@Override
	public Predicate toPredicate(Root<Driver> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

		Predicate predicate=criteriaBuilder.conjunction();
		if(filter.getId()!=null){
			predicate.getExpressions().add(criteriaBuilder.equal(root.get("id"), filter.getId()));
		}
		if(filter.getDriverLicenceNumber()!=null){
			predicate.getExpressions().add(criteriaBuilder.like(root.get("driverLicenceNumber"), "%"+filter.getDriverLicenceNumber()+"%")); 
		}
		if(filter.getFullName()!=null){
			predicate.getExpressions().add(criteriaBuilder.like(root.get("fullName"), "%"+filter.getFullName()+"%"));
		}
		if(filter.getIdCardNumber()!=null){
			predicate.getExpressions().add(criteriaBuilder.like(root.get("idCardNumber"), "%"+filter.getIdCardNumber()+"%"));
		}
		
		if(filter.getDriverLicenceDeliveryBeginDate()!=null){
			predicate.getExpressions().add(criteriaBuilder.greaterThanOrEqualTo(root.get("driverLicenceDeliveryDate"), filter.getDriverLicenceDeliveryBeginDate()));
		}
		if(filter.getDriverLicenceDeliveryEndDate()!=null){
			predicate.getExpressions().add(criteriaBuilder.lessThanOrEqualTo(root.get("driverLicenceDeliveryDate"), filter.getDriverLicenceDeliveryEndDate()));
		}
		
		if(filter.getDriverLicenceExpirationBeginDate()!=null){
			predicate.getExpressions().add(criteriaBuilder.greaterThanOrEqualTo(root.get("driverLicenceExpirationDate"), filter.getDriverLicenceExpirationBeginDate()));
		}
		if(filter.getDriverLicenceExpirationEndDate()!=null){
			predicate.getExpressions().add(criteriaBuilder.lessThanOrEqualTo(root.get("driverLicenceExpirationDate"), filter.getDriverLicenceExpirationEndDate()));
		}
				
		return criteriaBuilder.and(predicate);
	}

}
