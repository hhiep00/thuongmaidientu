package com.hiephk.payload.request;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RatingRequest implements Serializable{
	private int userId;
	private float rating;
	private String comment;
}
