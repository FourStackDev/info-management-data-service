package org.fourstack.personaldataservice.services;

import java.util.List;

import org.fourstack.personaldataservice.entities.Language;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LanguageService {
	public List<Language> getAllLanguages();

	public Page<Language> getLanguagePage(Pageable pageable);

	public Language getLanguageById(Long id);

	public Language saveLanguage(Language language);

	public List<Language> saveLanguages(List<Language> languageList);
}
