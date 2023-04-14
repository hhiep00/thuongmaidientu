package com.hiephk.constance;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ObjectResponse {
	private int status;
	private String message;
	private Object data;
}
