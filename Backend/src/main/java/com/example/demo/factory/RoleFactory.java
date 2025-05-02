package com.example.demo.factory;

import com.example.demo.entity.ERole;
import com.example.demo.entity.Role;
import com.example.demo.exception.RoleNotFoundException;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleFactory {
    @Autowired
    RoleRepository roleRepository;

    public Role getInstance(String role) throws RoleNotFoundException {
        switch (role) {
            case "admin" -> {
                return roleRepository.findByName(ERole.ROLE_ADMIN);
            }
            case "user" -> {
                return roleRepository.findByName(ERole.ROLE_USER);
            }
            case "super_admin" -> {
                return roleRepository.findByName(ERole.ROLE_SUPER_ADMIN);
            }
            case "bin" -> {
                return roleRepository.findByName(ERole.ROLE_BIN);
            }
            default -> throw  new RoleNotFoundException("No role found for " +  role);
        }
    }
}
