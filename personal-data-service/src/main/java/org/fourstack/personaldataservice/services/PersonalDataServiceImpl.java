package org.fourstack.personaldataservice.services;

import java.util.List;
import java.util.Optional;

import org.fourstack.personaldataservice.entities.Person;
import org.fourstack.personaldataservice.repositories.PersonalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PersonalDataServiceImpl implements PersonalDataService {
	@Autowired
	private PersonalDataRepository personalDataRepository;
	
	@Override
	public List<Person> getAllPersonData() {
		return personalDataRepository.findAll();
	}

	@Override
	public Page<Person> getAllPersonData(Pageable pageable) {
		return personalDataRepository.findAll(pageable);
	}

	@Override
	public Person getPersonById(Long id) {
		Optional<Person> personContainer = personalDataRepository.findById(id);
		return personContainer.get();
	}

	@Override
	public Person savePerson(Person person) {
		return personalDataRepository.save(person);
	}

	@Override
	public List<Person> saveAllPersons(List<Person> persons) {
		return personalDataRepository.saveAll(persons);
	}

}
