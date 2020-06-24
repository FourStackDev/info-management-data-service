package org.fourstack.personaldataservice.utils;

import java.util.Arrays;
import java.util.List;

import org.fourstack.personaldataservice.codetype.FluencyType;
import org.fourstack.personaldataservice.codetype.Proficiency;
import org.fourstack.personaldataservice.entities.Language;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EntityGenerator {

	private static ObjectMapper mapper;
	static {
		mapper = new ObjectMapper();
	}

	/**
	 * Generates and Returns Pageable Object without Sorting.
	 * 
	 * @param pageNo   required Page number.
	 * @param pageSize Size of the page
	 * @return PageRequest Object
	 */
	public static PageRequest getPageableObject(int pageNo, int pageSize) {
		return PageRequest.of(pageNo, pageSize);
	}

	/**
	 * Generates and Return Page Object for required List content.
	 * 
	 * @param <T>      Object parameter that requires Page
	 * @param pageable Pageable Object for creation of Page.
	 * @param content  List of T Objects
	 * @param total    total number of objects
	 * @return PageImpl Object with list content.
	 */
	public static <T> PageImpl<T> getPageObject(Pageable pageable, List<T> content, long total) {
		return new PageImpl<T>(content, pageable, total);
	}

	/**
	 * Generates and Return Language Object without an id.
	 * 
	 * @return Language Object.
	 */
	public static Language getLanguage() {
		Language language = new Language();
		language.setName("Kannada");
		language.setFluency(Arrays.asList(FluencyType.READ, FluencyType.WRITE, FluencyType.SPEAK));
		language.setProficiency(Proficiency.EXPERT);
		return language;
	}

	/**
	 * Generates and Returns Language Object with specified Object.
	 * 
	 * @param id Id required for Language Object.
	 * @return Language Object.
	 */
	public static Language getLanguage(Long id) {
		Language language = getLanguage();
		language.setId(id);
		return language;
	}

	/**
	 * Generates and Returns List of Language Objects.<br>
	 * Objects list will be generated by reading the json content from
	 * languages-list.json file.
	 * 
	 * @return List of Language Objects.
	 */
	public static List<Language> getLanguageList() {
		Language[] languages = {};
		try {
			languages = mapper.readValue(CommonUtils.getFileContent("json-files/languages-list.json"),
					Language[].class);
		} catch (JsonProcessingException e) {
			log.error("JsonProcessingException occurred while parsing the Json: languages-list.json", e);
		}
		return Arrays.asList(languages);
	}

}
