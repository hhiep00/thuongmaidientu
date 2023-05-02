package com.hiephk.payload.request;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequest implements Serializable{
	private String name;
	private String description;
	private int price;
	private String brand;
	private String category;
	private String image;
}
