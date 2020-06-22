package org.fourstack.personaldataservice.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.fourstack.personaldataservice.codetype.GenderType;
import org.fourstack.personaldataservice.codetype.MaritalStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "person")
@Data
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "firstname", nullable = false)
	@JsonProperty(value = "first_name")
	private String firstName;

	@Column(name = "middlename")
	@JsonProperty(value = "middle_name")
	private String middleName;

	@Column(name = "lastname", nullable = false)
	@JsonProperty(value = "last_name")
	private String lastName;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "yyyy/MM/dd")
	@Column(name = "date_of_birth", nullable = false)
	@JsonProperty(value = "birth_date")
	private LocalDate dateOfBirth;

	@Column(name = "gender", nullable = false)
	@Enumerated(EnumType.STRING)
	@JsonProperty(value = "gender")
	private GenderType gender;

	@Column(name = "fathername")
	@JsonProperty(value = "father_name")
	private String fatherName;

	@Column(name = "mothername")
	@JsonProperty(value = "mother_name")
	private String motherName;

	@Column(name = "marital_status")
	@Enumerated(EnumType.STRING)
	@JsonProperty(value = "marital_status")
	private MaritalStatus maritalStatus;
	
	/*
	 * ****************************************************************************
	 * ************* Start of Mapping columns with other Entities *****************
	 * ****************************************************************************
	 */
	
	@OneToOne(fetch = FetchType.EAGER, cascade = {
			CascadeType.MERGE,
			CascadeType.REFRESH,
			CascadeType.PERSIST,
			CascadeType.REMOVE
	})
	@JsonProperty(value = "contact_information")
	private ContactInfo contactInfo;

	@OneToOne(fetch = FetchType.EAGER, cascade = {
			CascadeType.MERGE,
			CascadeType.REFRESH,
			CascadeType.PERSIST,
			CascadeType.REMOVE
	})
	@JsonProperty(value = "address")
	private Address address;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {
			CascadeType.MERGE,
			CascadeType.REFRESH,
			CascadeType.PERSIST,
			CascadeType.REMOVE
	}, orphanRemoval = true)
	@JoinColumn(name = "person_id")
	@JsonProperty(value = "languages")
	private List<Language> languages = new ArrayList<>();
	
	/*
	 * ****************************************************************************
	 * ************** End of Mapping columns with other Entities ******************
	 * ****************************************************************************
	 */
	
	/**
	 * createDateTime and updateDateTime columns are used to track the insertion and
	 * updation of Entities at database level
	 */
	@JsonIgnore
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	@CreationTimestamp
	@Column(name = "created_date", nullable = false, updatable = false)
	private LocalDateTime createDateTime;
	
	@JsonIgnore
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	@UpdateTimestamp
	@Column(name = "updated_date", nullable = false, updatable = true)
	private LocalDateTime updateDateTime;
}
