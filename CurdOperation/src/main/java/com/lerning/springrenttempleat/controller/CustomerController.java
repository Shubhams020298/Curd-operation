package com.lerning.springrenttempleat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lerning.springrenttempleat.model.CustomerModel;
import com.lerning.springrenttempleat.service.ICustomerDao;
//import com.sun.el.stream.Optional;
import java.util.Optional;

@RestController
public class CustomerController {

	@Autowired
	ICustomerDao iDao;
	
	//@RequestMapping(value = "/add",method = RequestMethod.get)
	@GetMapping("/")
	public ResponseEntity<String> greek()
	{
		return new ResponseEntity<String>("Hello Spring Boot", HttpStatus.OK);
	}
	
	@RequestMapping("/create")
	public ResponseEntity<String> createNew()
	{
		CustomerModel model=new CustomerModel(121, "karan", "karan@gmail.com", "goregaon");
		CustomerModel cust=iDao.save(model);
		if(cust!=null)
		{
			return new ResponseEntity<String>("Record Add", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Record Not Add", HttpStatus.CONFLICT);
	}
	
	/*@PostMapping("/add")
	public ResponseEntity<HttpStatus> addNew(@RequestBody CustomerModel model)
	{
		//CustomerModel model=new CustomerMo  del(2, "suraj", "suraj@gmail.com", "Jogeshwari");
		CustomerModel cust=iDao.save(model);
		if(cust!=null)
		{
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}*/
	
	
	@PostMapping("/add")
	public ResponseEntity<HttpStatus> addNew(@RequestBody CustomerModel model)
	{
		CustomerModel cm=iDao.save(model);
		if(cm!=null)
		{
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	/*
	@RequestMapping("/add")
	public String addNew()
	{
		CustomerModel cust=new CustomerModel(101, "Pooja", "Pooja@gmail.com", "Kurla");
		CustomerModel model=iDao.save(cust);
		if(model!=null)
		{
			return "Record Add";
		}
		return "Record Not Add";
	}*/
	
	@GetMapping("/detail")
	public ResponseEntity<List<CustomerModel>> getDetaile()
	{
		List<CustomerModel> customer=(List<CustomerModel>)iDao.findAll();
		return new ResponseEntity<List<CustomerModel>>(customer, HttpStatus.OK);  
	}
	
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<Optional<CustomerModel>> getDetaileById(@PathVariable int id)
	{	
		//When We Use optional key word then "isPresent" Is come Otherwise is present is not there 
		//Optional<CustomerModel> cust=(Optional<CustomerModel>)iDao.findById(id);
		Optional<CustomerModel> cust= (Optional<CustomerModel>)iDao.findById(id);
		if(cust.isPresent())
		{
		return new ResponseEntity<Optional<CustomerModel>>(cust,HttpStatus.OK);
		}
		return new ResponseEntity<Optional<CustomerModel>>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/update")
	public ResponseEntity<HttpStatus> getupdate(@RequestBody CustomerModel model)
	{
		CustomerModel cust=iDao.save(model);
		if(cust!=null)
		{
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.CONFLICT);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable int id)
	{
		Optional<CustomerModel> cust= (Optional<CustomerModel>)iDao.findById(id);
		if(cust.isPresent())
		{
			iDao.deleteById(id);
			return new ResponseEntity<>(HttpStatus.GONE);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	
	
}
