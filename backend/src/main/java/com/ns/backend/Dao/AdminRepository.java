package com.ns.backend.Dao;

import com.ns.backend.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Integer> {

    Optional<Admin> findByName (String name);

}

