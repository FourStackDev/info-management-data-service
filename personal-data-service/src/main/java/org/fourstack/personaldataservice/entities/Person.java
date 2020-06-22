package org.fourstack.personaldataservice.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.fourstack.personaldataservice.codetype.GenderType;
import org.fourstack.personaldataservice.codetype.MaritalStatus;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

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
}
