package com.adsnet.subscription.repositories;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.adsnet.subscription.entities.TopUp;

/**
 * This class is used to search Drivers specifying the criteria
 *
 */
public class TopUpSpecification implements Specification<TopUp>{

	private TopUp filter;
	
	public TopUpSpecification(TopUp filter) {
		super();
		this.filter=filter;
	}
	 
	@Override
	public Predicate toPredicate(Root<TopUp> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

		Predicate predicate=criteriaBuilder.conjunction();
		if(filter.getId()!=null){
			predicate.getExpressions().add(criteriaBuilder.equal(root.get("id"), filter.getId()));
		}
		
		if(filter.getAmount()!=null){
			predicate.getExpressions().add(criteriaBuilder.equal(root.get("amount"), filter.getAmount()));
		}
		
		if(filter.getTopUpType()!=null){
			predicate.getExpressions().add(criteriaBuilder.equal(root.get("topUpType"), filter.getTopUpType()));
		}
		
		if(filter.getAccount()!=null){
			if(filter.getAccount().getId()!=null){
				predicate.getExpressions().add(criteriaBuilder.equal(root.join("account").get("id"), filter.getAccount().getId()));
			}
		}
		
		if(filter.getCreationDateBegin()!=null){
			predicate.getExpressions().add(criteriaBuilder.greaterThanOrEqualTo(root.get("creationDate"), filter.getCreationDateBegin()));
		}
		if(filter.getCreationDateEnd()!=null){
			predicate.getExpressions().add(criteriaBuilder.lessThanOrEqualTo(root.get("creationDate"), filter.getCreationDateEnd()));
		}	
		
		
		return criteriaBuilder.and(predicate);
	}

}
