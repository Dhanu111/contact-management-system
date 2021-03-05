package com.contactManager.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.contactManager.api.dao.UserContactRepository;
import com.contactManager.api.model.UserContact;

@RestController
public class UserContactController {
	@Autowired
	private UserContactRepository repository;
	
	@PostMapping("/saveContact")
	@CrossOrigin(origins = "http://localhost:3000")
	public String SaveEmployee(@RequestBody UserContact userContact) {
		repository.save(userContact);
		return "contact saved successfully";
	}
	@GetMapping("/getAllContact")
	@CrossOrigin(origins = "http://localhost:3000")
	public List<UserContact> getAllContacts(){
		return repository.findAll();
	}
	@GetMapping("/getContact/{email}")
	@CrossOrigin(origins = "http://localhost:3000")
	public UserContact getByEmail(@PathVariable String email) {
		return repository.findByEmail(email);
		
	}
	
	@GetMapping("/getContactByStatus/{status}")
	@CrossOrigin(origins = "http://localhost:3000")
	public List<UserContact> getByStatus(@PathVariable String status){
		return repository.findByStatus(status);
	}
	@PutMapping("/updateContact")
	@CrossOrigin(origins = "http://localhost:3000")
	public UserContact updateContacts(@RequestBody UserContact newUserContact) {
		return repository.findById(newUserContact.getContactID())
			      .map(contact -> {
			        contact.setFirstName(newUserContact.getFirstName());
			        contact.setLastName(newUserContact.getLastName());
			        contact.setEmail(newUserContact.getEmail());
			        contact.setPhoneNumber(newUserContact.getPhoneNumber());
			        contact.setStatus(newUserContact.getStatus());
			        return repository.save(contact);
			      })
			      .orElseGet(() -> {
			    	  //newUserContact.setContactID(contactID);
			        return repository.save(newUserContact);
			      });
	}
	
	@DeleteMapping("/deleteContact/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	  void deleteEmployee(@PathVariable int id) {
	    repository.deleteById(id);
	  }
	
	
}
