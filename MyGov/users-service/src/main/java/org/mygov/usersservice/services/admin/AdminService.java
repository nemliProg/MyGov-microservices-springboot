package org.mygov.usersservice.services.admin;

import org.mygov.usersservice.etities.Admin;

public interface AdminService {

    Admin loadAdminByEmail(String email);


}
