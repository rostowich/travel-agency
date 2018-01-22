package com.adsnet.voyage.repositories;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.adsnet.voyage.entities.Voyage;

public class VoyageSpecification implements Specification<Voyage> {

	private Voyage filter;
	
	public VoyageSpecification(Voyage filter) {
		super();
		this.filter=filter;
	}
	
	@Override
	public Predicate toPredicate(Root<Voyage> root, CriteriaQuery<?> arg1, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		Predicate predicate=criteriaBuilder.conjunction();
		if(filter.getId()!=null){
			predicate.getExpressions().add(criteriaBuilder.equal(root.get("id"), filter.getId()));
		}
		if(filter.getArchived()!=null){
			predicate.getExpressions().add(criteriaBuilder.equal(root.get("archived"), filter.getArchived())); 
		}
		if(filter.getDepartureDate()!=null){
			predicate.getExpressions().add(criteriaBuilder.equal(root.get("departureDate"), filter.getDepartureDate()));
		}
		if(filter.getNumberOfPlace()!=0){
			predicate.getExpressions().add(criteriaBuilder.equal(root.get("numberOfPlace"), filter.getNumberOfPlace()));
		}
		
		if(filter.getBusId()!=null){
			predicate.getExpressions().add(criteriaBuilder.equal(root.get("busId"), filter.getBusId()));
		}
		
		if(filter.getDepartureHourId()!=null){
			predicate.getExpressions().add(criteriaBuilder.equal(root.get("departureHourId"), filter.getDepartureHourId()));
		}
		
		if(filter.getPathId()!=null){
			predicate.getExpressions().add(criteriaBuilder.equal(root.get("pathId"), filter.getPathId()));
		}
				
		return criteriaBuilder.and(predicate);
	}

}
