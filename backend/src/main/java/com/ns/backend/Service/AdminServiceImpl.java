package com.ns.backend.Service;

import com.ns.backend.Dao.AdminRepository;
import com.ns.backend.Entity.Admin;
import org.springframework.stereotype.Service;
import java.util.Optional;



@Service
public class AdminServiceImpl implements AdminService  {

    private AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin findByName(String name) {


        Optional<Admin> result = adminRepository.findByName(name);

        Admin admin = null;

        if(result.isPresent()){
            admin = result.get();
        }else {
            throw new RuntimeException("Did not find");
        }

        return admin;
    }
}
