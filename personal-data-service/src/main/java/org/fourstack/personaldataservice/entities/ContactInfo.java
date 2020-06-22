package org.fourstack.personaldataservice.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ColumnTransformer;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "contact_info", uniqueConstraints = { @UniqueConstraint(columnNames = "mailId"),
		@UniqueConstraint(columnNames = "primary_contact_number") })
@Data
public class ContactInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3600142715422312701L;

	@Id
	@Column(name = "contact_info_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "primary_contact_number")
	@JsonProperty(value = "prim_contact_num")
	private String primaryContactNumber;

	@Column(name = "secondary_contact_number")
	@JsonProperty(value = "sec_contact_num")
	private String secondaryContactNumber;

	@Column(name = "mailId")
	@ColumnTransformer(forColumn = "mailId", write = "LOWER(?)")
	@JsonProperty(value = "mail_id")
	private String mailId;

}
