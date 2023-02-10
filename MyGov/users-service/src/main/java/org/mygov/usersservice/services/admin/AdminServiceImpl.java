package org.mygov.usersservice.services.admin;

import org.mygov.usersservice.etities.Admin;
import org.mygov.usersservice.repositoies.AdminRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin loadAdminByEmail(String email){
        return this.adminRepository.findAdminByEmail(email).orElse(null);
    }


}
