package com.hiephk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiephk.model.Role;
import com.hiephk.repository.RoleRepo;

@Service
public class RoleService{
	@Autowired
	private RoleRepo roleRepo;
	
	public List<Role> getAllRoles(){
		return roleRepo.findAll();
	}
	
	public Role getRoleById(int roleId) {
		return roleRepo.findById(roleId).get();
	}
}
