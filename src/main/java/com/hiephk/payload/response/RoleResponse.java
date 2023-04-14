package com.hiephk.payload.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleResponse {
	private String role;
	private String description;
}
