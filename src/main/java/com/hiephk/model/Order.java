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
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int _id;
	@Column
	private float totalPrice;
	@Column
	private Date createdAt;
	@Column
	private Date updatedAt;
	@Column
	private int userId;
}
