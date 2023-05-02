package com.hiephk.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int _id;
	@Column
	private String name;
	@Column
	private String description;
	@Column
	private String category; 
	@Column
	private String brand; 
	@Column
	private Date createdAt;
	@Column
	private int views;
	@Column
	private int price;
	@Column
	private String image;
}
