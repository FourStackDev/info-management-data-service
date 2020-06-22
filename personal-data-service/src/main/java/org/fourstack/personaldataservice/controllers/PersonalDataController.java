package org.fourstack.personaldataservice.controllers;

import java.util.List;

import org.fourstack.personaldataservice.entities.Person;
import org.fourstack.personaldataservice.repositories.PersonalDataRepository;
import org.fourstack.personaldataservice.services.PersonalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class PersonalDataController {
	
	@Autowired
	private PersonalDataService personalDataService;
	
	@Autowired
	PersonalDataRepository respository;

	@GetMapping
	public List<Person> getPersonsList() {
		return personalDataService.getAllPersonData();		
	}
	
	@PostMapping("/person")
	public Person savePerson(@RequestBody Person person) {
		return personalDataService.savePerson(person);
	}
	
	@PutMapping("/person/{id}")
	public Person updatePerson(@RequestBody Person person) {
		return personalDataService.savePerson(person);
	}
}
