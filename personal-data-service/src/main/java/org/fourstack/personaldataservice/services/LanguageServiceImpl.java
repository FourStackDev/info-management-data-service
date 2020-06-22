package org.fourstack.personaldataservice.services;

import java.util.List;
import java.util.Optional;

import org.fourstack.personaldataservice.entities.Language;
import org.fourstack.personaldataservice.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class LanguageServiceImpl implements LanguageService {

	@Autowired
	private LanguageRepository languageRepository;

	@Override
	public List<Language> getAllLanguages() {
		return languageRepository.findAll();
	}

	@Override
	public Page<Language> getLanguagePage(Pageable pageable) {
		return languageRepository.findAll(pageable);
	}

	@Override
	public Language getLanguageById(Long id) {
		Optional<Language> optionalLanguage = languageRepository.findById(id);
		return optionalLanguage.get();
	}

	@Override
	public Language saveLanguage(Language language) {
		return languageRepository.save(language);
	}

	@Override
	public List<Language> saveLanguages(List<Language> languageList) {
		return languageRepository.saveAll(languageList);
	}

}
