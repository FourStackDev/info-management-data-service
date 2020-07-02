package org.fourstack.personaldataservice.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.fourstack.personaldataservice.entities.ContactInfo;
import org.fourstack.personaldataservice.repositories.ContactInfoRepository;
import org.fourstack.personaldataservice.services.ContactInfoServiceImpl;
import org.fourstack.personaldataservice.utils.CommonUtils;
import org.fourstack.personaldataservice.utils.EntityGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContactInfoServiceTest {
	@Mock
	private ContactInfoRepository repository;

	@InjectMocks
	private ContactInfoServiceImpl contactInfoService;

	@BeforeEach
	public void initializeMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_getAllContactInfo() {
		log.info("Start of test_getAllContactInfo() method");
		List<ContactInfo> mockContactInfoList = EntityGenerator.getContactInfoList();

		// mock the Repository
		when(repository.findAll()).thenReturn(mockContactInfoList);

		// call the service layer method
		List<ContactInfo> actualContactInfoList = contactInfoService.getAllContactInfo();

		// verify the results
		assertEquals(mockContactInfoList.size(), actualContactInfoList.size());
		assertEquals(mockContactInfoList, actualContactInfoList);
		log.info("End of test_getAllContactInfo() method");
	}

	@Test
	public void test_getContactInfoPage() {
		log.info("Start of test_getContactInfoPage() method");
		List<ContactInfo> mockContactInfoList = EntityGenerator.getContactInfoList();

		// get Page Request
		PageRequest pageable = EntityGenerator.getPageableObject(1, 10);

		// get Page Object for the mock ContactInfo List
		PageImpl<ContactInfo> contactInfoPage = EntityGenerator.getPageObject(pageable, mockContactInfoList,
				mockContactInfoList.size());

		// mock the Repository
		when(repository.findAll(pageable)).thenReturn(contactInfoPage);

		// call the service layer method
		Page<ContactInfo> actualContactInfoPage = contactInfoService.getContactInfoPage(pageable);
		List<ContactInfo> actualContactInfoList = actualContactInfoPage.getContent();

		// verify the Results
		assertEquals(mockContactInfoList.size(), actualContactInfoList.size());
		assertEquals(mockContactInfoList, actualContactInfoList);
		log.info("End of test_getContactInfoPage() method");
	}

	@Test
	public void test_getContactInfoById() {
		log.info("Start of test_getContactInfoById() method");
		long id = CommonUtils.getRandomLong();
		ContactInfo mockContactInfo = EntityGenerator.getConatctInfo(id);

		// mock the Repository
		when(repository.findById(id)).thenReturn(Optional.of(mockContactInfo));

		// call the Service Layer
		ContactInfo actualContactInfo = contactInfoService.getContactInfoById(id);

		// verify the Results
		assertEquals(mockContactInfo.getId(), actualContactInfo.getId());
		assertEquals(mockContactInfo.getMailId(), actualContactInfo.getMailId());
		assertEquals(mockContactInfo.getPrimaryContactNumber(), actualContactInfo.getPrimaryContactNumber());
		assertEquals(mockContactInfo.getSecondaryContactNumber(), actualContactInfo.getSecondaryContactNumber());
		assertEquals(mockContactInfo, actualContactInfo);
		log.info("End of test_getContactInfoById() method");
	}

	@Test
	public void test_saveContactInfo() {
		log.info("Start of test_saveContactInfo() method");
		ContactInfo mockContactInfo = EntityGenerator.getContactInfo();

		// mock the Repository
		when(repository.save(mockContactInfo)).thenReturn(mockContactInfo);

		// call the Service Layer
		ContactInfo savedContactInfo = contactInfoService.saveContactInfo(mockContactInfo);
		log.info("Saved ContactInfo: "+savedContactInfo);

		// verify the Results
		assertEquals(mockContactInfo.getMailId(), savedContactInfo.getMailId());
		assertEquals(mockContactInfo.getPrimaryContactNumber(), savedContactInfo.getPrimaryContactNumber());
		assertEquals(mockContactInfo.getSecondaryContactNumber(), savedContactInfo.getSecondaryContactNumber());
		assertEquals(mockContactInfo, savedContactInfo);
		log.info("End of test_saveContactInfo() method");
	}

	@Test
	public void test_saveContactInfos() {
		log.info("Start of test_saveContactInfos() method");
		List<ContactInfo> mockContactInfoList = EntityGenerator.getContactInfoList();

		// mock the repository Layer
		when(repository.saveAll(mockContactInfoList)).thenReturn(mockContactInfoList);

		// call the Service Layer
		List<ContactInfo> savedContactInfoList = contactInfoService.saveContactInfos(mockContactInfoList);

		// verify the Results
		assertEquals(mockContactInfoList.size(), savedContactInfoList.size());
		assertEquals(mockContactInfoList, savedContactInfoList);
		log.info("End of test_saveContactInfos() method");
	}
}
