package com.adsnet.subscription.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.adsnet.subscription.entities.Account;
import com.adsnet.subscription.enums.AccountType;
import com.adsnet.subscription.exceptions.ItemAlreadyExistException;
import com.adsnet.subscription.repositories.IAccountRepository;
import com.adsnet.subscription.utils.UtilityFactory;

@Service
public class AccountService implements IAccountService{

	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	private IAccountRepository accountRepository;
	
	@Autowired 
	private UtilityFactory datetime;
		
	@Override
	public Account save(Account account) throws ItemAlreadyExistException{
		//We need to check if this account already exists 
		Optional<Account> accountExist1=accountRepository.findByNumber(account.getNumber());
		Optional<Account> accountExist2=accountRepository.findByLabel(account.getLabel());
		if(accountExist1.isPresent())
			throw new ItemAlreadyExistException("Un compte avec ce numero existe déjà dans le système");
		if(accountExist2.isPresent())
			throw new ItemAlreadyExistException("Un compte avec ce nom existe déjà dans le système");
		
		account=initializeAccountForCreation(account);
		return accountRepository.save(account);
	}
	
	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return accountRepository.findAll();
	}

	@Override
	public void delete(Long accountId) {
		accountRepository.delete(accountId);
	}
	
	@Override
	public Page<Account> findAllByExample(Account account, Pageable pageable) {
		// TODO Auto-generated method stub
		ExampleMatcher matcher=ExampleMatcher.matching()
				.withMatcher("id", match -> match.exact())
				.withMatcher("label", match -> match.contains().ignoreCase())
				.withMatcher("number", match -> match.contains().ignoreCase())
				.withMatcher("accountType", match -> match.contains().ignoreCase())
				.withMatcher("accountLimitType", match -> match.contains().ignoreCase())
				.withMatcher("amountLimitType", match -> match.contains().ignoreCase())
				.withIgnoreNullValues();
		
		Example<Account> example = Example.of(account, matcher);
		return accountRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Account> findAccountById(Long id) {
		// TODO Auto-generated method stub
		return accountRepository.findById(id);
	}

	@Override
	public Account update(Account account) {
		// TODO Auto-generated method stub
		account=initializeAccountForModification(account);
		return accountRepository.save(account);
	}
	
	
	private  Account initializeAccountForCreation(Account account){		
		account.setBalance(new Double(0));
		account.setCreationDate(datetime.getDate());
		account.setModificationDate(datetime.getDate());
		account.setModificationDate(new Date());
		if(account.getAccountType()==AccountType.DEPOSIT){
			account.setAmountLimit(null);
			account.setAccountLimitType(null);
		}
		return account;
	}
	
	private  Account initializeAccountForModification(Account account){		
		account.setModificationDate(datetime.getDate());
		if(account.getAccountType()==AccountType.DEPOSIT){
			account.setAmountLimit(null);
			account.setAccountLimitType(null);
		}
		return account;
	}
}	



	