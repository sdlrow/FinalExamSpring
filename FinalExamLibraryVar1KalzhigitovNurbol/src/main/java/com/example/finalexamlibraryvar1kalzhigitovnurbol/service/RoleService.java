package com.example.finalexamlibraryvar1kalzhigitovnurbol.service;

import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.*;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("RoleService")
public class RoleService {

    RoleRepository roleRepository;
    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = false)
    public Optional<Role> findByName(ERole name){
        return roleRepository.findByName(name);
    }
}
