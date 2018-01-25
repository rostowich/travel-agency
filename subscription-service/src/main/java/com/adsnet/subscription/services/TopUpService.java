package com.adsnet.subscription.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adsnet.subscription.entities.Account;
import com.adsnet.subscription.entities.TopUp;
import com.adsnet.subscription.enums.AccountType;
import com.adsnet.subscription.enums.TopUpType;
import com.adsnet.subscription.exceptions.AccountNotFoundException;
import com.adsnet.subscription.exceptions.InsuffisantAmountIntoAccountException;
import com.adsnet.subscription.repositories.IAccountRepository;
import com.adsnet.subscription.repositories.ITopUpRepository;
import com.adsnet.subscription.repositories.TopUpSpecification;
import com.adsnet.subscription.utils.UtilityFactory;


@Service
public class TopUpService implements ITopUpService{

	@Autowired
	private ITopUpRepository topUpRepository;
	
	@Autowired
	private IAccountRepository accountRepository;
	
	@Autowired 
	private UtilityFactory datetime;
	
	@Override
	@Transactional
	public TopUp save(TopUp topUp) throws InsuffisantAmountIntoAccountException, AccountNotFoundException{
		// TODO Auto-generated method stub
		Double amountIntoTopUp=Double.parseDouble(topUp.getAmount());
		//Finding the account
		Optional<Account> accountToTupUp=accountRepository.findById(topUp.getAccount().getId());
		if(!accountToTupUp.isPresent() || accountToTupUp.get().getAccountType()==AccountType.CREDIT)
			throw new AccountNotFoundException("Compte inexistant ou le compte est un compte de credit");
		
		if(topUp.getTopUpType()==TopUpType.CREDIT)
			accountToTupUp.get().setBalance(accountToTupUp.get().getBalance()+amountIntoTopUp);
		else{
			if(accountToTupUp.get().getBalance() < amountIntoTopUp)
				throw new InsuffisantAmountIntoAccountException();
			accountToTupUp.get().setBalance(accountToTupUp.get().getBalance()-amountIntoTopUp);
		}
		topUp=initializeTopUpForCreation(topUp);
		topUp.setAccount(accountToTupUp.get());
		return topUpRepository.save(topUp);
	}


	@Override
	public List<TopUp> findAll() {
		// TODO Auto-generated method stub
		return topUpRepository.findAll();
	}

	@Override
	public Page<TopUp> findAllByExample(TopUp topUp, Pageable pageable) {
		// TODO Auto-generated method stub
		Specification<TopUp> specification=new TopUpSpecification(topUp);
		return topUpRepository.findAll(specification, pageable);
	}

	@Override
	public Optional<TopUp> findTopUpById(Long id) {
		// TODO Auto-generated method stub
		return topUpRepository.findById(id);
	}
	
	private  TopUp initializeTopUpForCreation(TopUp topUp){		
		topUp.setCreationDate(datetime.getDate());
		return topUp;
	}
	
	

}
