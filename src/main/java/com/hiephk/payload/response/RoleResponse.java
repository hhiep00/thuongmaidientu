package com.hiephk.payload.response;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleResponse implements Serializable{
	private String role;
	private String description;
}
