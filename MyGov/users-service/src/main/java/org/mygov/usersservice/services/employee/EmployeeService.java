package org.mygov.usersservice.services.employee;

import org.mygov.usersservice.etities.Employee;

public interface EmployeeService {
    Employee loadAdminByEmail(String email);
}
