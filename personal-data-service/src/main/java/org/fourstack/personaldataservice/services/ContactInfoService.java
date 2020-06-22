package org.fourstack.personaldataservice.services;

import java.util.List;

import org.fourstack.personaldataservice.entities.ContactInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactInfoService {

	public List<ContactInfo> getAllContactInfo();

	public Page<ContactInfo> getContactInfoPage(Pageable pageable);

	public ContactInfo getContactInfoById(Long id);

	public ContactInfo saveContactInfo(ContactInfo contactInfo);

	public List<ContactInfo> saveContactInfos(List<ContactInfo> contactsList);

}
