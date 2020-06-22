package org.fourstack.personaldataservice.services;

import java.util.List;

import org.fourstack.personaldataservice.entities.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonalDataService {

	public List<Person> getAllPersonData();
	
	public Page<Person> getAllPersonData(Pageable pageable);
	
	public Person getPersonById(Long id);
	
	public Person savePerson(Person person);
	
	public List<Person> saveAllPersons(List<Person> persons);
}
