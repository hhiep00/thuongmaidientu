package com.hiephk.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "addresses")
@Data
@Builder
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int _id;
	@Column
	private String address;
	@Column
	private String city;
	@Column
	private String country;
}
