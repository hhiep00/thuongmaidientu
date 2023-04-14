package com.hiephk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiephk.constance.ObjectResponse;
import com.hiephk.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@GetMapping("")
	public ObjectResponse getAllRole() {
		return new ObjectResponse(200, "success", roleService.getAllRoles());
	}
	
	@GetMapping("/{roleId}")
	public ObjectResponse getOneRole(@PathVariable int roleId) {
		return new ObjectResponse(200, "success", roleService.getRoleById(roleId));
	}
}
