package com.adsnet.referential.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.adsnet.referential.entities.CashRegister;
import com.adsnet.referential.exceptions.ItemAlreadyExistException;
import com.adsnet.referential.repositories.ICashRegisterRepository;

@Service
public class CashRegisterService implements ICashRegisterService{

	@Autowired
	private ICashRegisterRepository cashRegisterRepository;
	
	@Override
	public CashRegister save(CashRegister cashRegister) throws ItemAlreadyExistException{
		// TODO Auto-generated method stub
		Optional<CashRegister> cashRegisterSearch=cashRegisterRepository.findByLabel(cashRegister.getLabel());
		if(cashRegisterSearch.isPresent())
			throw new ItemAlreadyExistException("Ce guichet existe déjà");
		return cashRegisterRepository.save(cashRegister);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		cashRegisterRepository.delete(id);
	}

	@Override
	public Optional<CashRegister> findById(Long id) {
		// TODO Auto-generated method stub
		return cashRegisterRepository.findById(id);
	}

	@Override
	public List<CashRegister> findAll() {
		// TODO Auto-generated method stub
		return cashRegisterRepository.findAll();
	}

	@Override
	public Page<CashRegister> findAllByExample(CashRegister cashRegister, Pageable pageable) {
		// TODO Auto-generated method stub
		ExampleMatcher matcher=ExampleMatcher.matching()
				.withMatcher("id", match -> match.exact())
				.withMatcher("label", match ->match.contains().ignoreCase());
		
		Example<CashRegister> example = Example.of(cashRegister, matcher);
		return cashRegisterRepository.findAll(example, pageable);
	}

}
