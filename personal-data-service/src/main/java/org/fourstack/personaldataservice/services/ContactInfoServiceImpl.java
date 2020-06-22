package org.fourstack.personaldataservice.services;

import java.util.List;
import java.util.Optional;

import org.fourstack.personaldataservice.entities.ContactInfo;
import org.fourstack.personaldataservice.repositories.ContactInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ContactInfoServiceImpl implements ContactInfoService {

	@Autowired
	private ContactInfoRepository contactRepository;

	@Override
	public List<ContactInfo> getAllContactInfo() {
		return contactRepository.findAll();
	}

	@Override
	public Page<ContactInfo> getContactInfoPage(Pageable pageable) {
		return contactRepository.findAll(pageable);
	}

	@Override
	public ContactInfo getContactInfoById(Long id) {
		Optional<ContactInfo> optionalContact = contactRepository.findById(id);
		return optionalContact.get();
	}

	@Override
	public ContactInfo saveContactInfo(ContactInfo contactInfo) {
		return contactRepository.save(contactInfo);
	}

	@Override
	public List<ContactInfo> saveContactInfos(List<ContactInfo> contactsList) {
		return contactRepository.saveAll(contactsList);
	}

}
