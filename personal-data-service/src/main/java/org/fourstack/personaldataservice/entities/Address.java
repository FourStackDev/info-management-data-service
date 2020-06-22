package org.fourstack.personaldataservice.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "address")
@Data
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4899516233883693079L;

	@Id
	@Column(name = "address_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "door_num")
	@JsonProperty(value = "door_number")
	private String doorNo;

	@Column(name = "address_line1", columnDefinition = "TEXT")
	@JsonProperty(value = "addressLine1")
	private String addressLine1;

	@Column(name = "address_line2", columnDefinition = "TEXT")
	@JsonProperty(value = "addressLine2")
	private String addressLine2;
	
	@Column(name = "taluk")
	@JsonProperty(value = "taluk")
	private String taluk;

	@Column(name = "ditrict")
	@JsonProperty(value = "ditrict")
	private String district;

	@Column(name = "state")
	@JsonProperty(value = "state")
	private String state;

	@Column(name = "country")
	@JsonProperty(value = "country")
	private String country;

	@Column(name = "pincode")
	@JsonProperty(value = "pincode")
	private String pincode;
}
