package org.fourstack.personaldataservice.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.fourstack.personaldataservice.entities.Language;
import org.fourstack.personaldataservice.repositories.LanguageRepository;
import org.fourstack.personaldataservice.services.LanguageServiceImpl;
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
public class LanguageServiceTest {

	@Mock
	private LanguageRepository languageRepository;

	@InjectMocks
	private LanguageServiceImpl languageService;

	@BeforeEach
	public void initializeMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_getAllLanguages() {
		log.info("LanguageServiceTest: Strat of test_getAllLanguages() method");
		List<Language> mockLanguageList = EntityGenerator.getLanguageList();
		// mock the repository
		when(languageRepository.findAll()).thenReturn(mockLanguageList);

		// call the service method
		List<Language> languageList = languageService.getAllLanguages();

		// verify the result
		assertEquals(mockLanguageList.size(), languageList.size());
		assertEquals(mockLanguageList, languageList);
		log.info("LanguageServiceTest: End of test_getAllLanguages() method");
	}

	@Test
	public void test_getLanguageById() {
		log.info("LanguageServiceTest: Strat of test_getLanguageById() method");
		long id = CommonUtils.getRandomLong();
		Language mockLanguage = EntityGenerator.getLanguage(id);

		// mock the repository
		when(languageRepository.findById(id)).thenReturn(Optional.of(mockLanguage));

		// call the service layer method
		Language language = languageService.getLanguageById(id);

		// verify the results
		assertEquals(mockLanguage.getId(), language.getId());
		assertEquals(mockLanguage.getFluency(), language.getFluency());
		assertEquals(mockLanguage.getName(), language.getName());
		assertEquals(mockLanguage.getProficiency(), language.getProficiency());
		assertEquals(mockLanguage, language);
		log.info("LanguageServiceTest: Strat of test_getLanguageById() method");
	}
	
	@Test
	public void test_getLanguagePage() {
		log.info("LanguageServiceTest: Strat of test_getLanguagePage() method");
		List<Language> mockLanguages = EntityGenerator.getLanguageList();
		
		// get PageRequest Object
		PageRequest pageable = EntityGenerator.getPageableObject(1, 10);
		
		// Create Page Object		
		PageImpl<Language> languagePage = EntityGenerator.getPageObject(pageable, mockLanguages, mockLanguages.size());
		
		// mock the repository
		when(languageRepository.findAll(pageable)).thenReturn(languagePage);
		
		// call the service layer
		Page<Language> actualLanguagePage = languageService.getLanguagePage(pageable);
		List<Language> actualLanguageList = actualLanguagePage.getContent();
		
		// verify the results
		assertEquals(mockLanguages.size(), actualLanguageList.size());
		
		log.info("LanguageServiceTest: Strat of test_getLanguagePage() method");
	}

	@Test
	public void test_saveLanguage() {
		log.info("LanguageServiceTest: Strat of test_saveLanguage() method");
		Language mockLanguage = EntityGenerator.getLanguage();

		// mock the repository
		when(languageRepository.save(mockLanguage)).thenReturn(mockLanguage);

		// call the service layer method
		Language savedLanguage = languageService.saveLanguage(mockLanguage);

		log.info("Saved Language Object: " + savedLanguage);

		// verify the results
		assertEquals(mockLanguage.getName(), savedLanguage.getName());
		assertEquals(mockLanguage.getFluency(), savedLanguage.getFluency());
		assertEquals(mockLanguage.getProficiency(), savedLanguage.getProficiency());
		assertEquals(mockLanguage, savedLanguage);

		log.info("LanguageServiceTest: Strat of test_saveLanguage() method");
	}

	@Test
	public void test_saveLanguages() {
		log.info("LanguageServiceTest: Strat of test_saveLanguages() method");
		List<Language> mockLanguages = EntityGenerator.getLanguageList();

		// mock the repository
		when(languageRepository.saveAll(mockLanguages)).thenReturn(mockLanguages);

		// call the service layer method
		List<Language> savedLanguages = languageService.saveLanguages(mockLanguages);

		// verify the results
		assertEquals(mockLanguages.size(), savedLanguages.size());
		assertEquals(mockLanguages, savedLanguages);
		log.info("LanguageServiceTest: Strat of test_saveLanguages() method");
	}
}
