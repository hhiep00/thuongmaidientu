package com.hiephk.model;

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
	private String orderStatus;
	@Column
	private String paymentStatus;
	@Column
	private String shipmentStatus;
	@Column
	private int totalPrice;
	@Column
	private int paymentId;
	@Column
	private int shipmentId;
	@Column
	private int userId;
}
