package com.lerning.springrenttempleat.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lerning.springrenttempleat.model.CustomerModel;

public interface ICustomerDao extends JpaRepository<CustomerModel, Integer> {
	
	
}
