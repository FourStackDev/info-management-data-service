package org.fourstack.personaldataservice.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.fourstack.personaldataservice.codetype.FluencyType;
import org.fourstack.personaldataservice.codetype.Proficiency;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "language")
@Data
public class Language implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5351720320217465190L;
	
	@Id
	@Column(name = "language_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "language_name")
	@JsonProperty(value = "language_name")
	private String name;
	
	@Column(name = "proficiency")
	@Enumerated(EnumType.STRING)
	@JsonProperty(value  = "proficiency")
	private Proficiency proficiency;
	
	@Column(name = "fluency")
	@ElementCollection
	@Enumerated(EnumType.STRING)
	@JsonProperty(value = "fluency")
	private List<FluencyType> fluency;
}
