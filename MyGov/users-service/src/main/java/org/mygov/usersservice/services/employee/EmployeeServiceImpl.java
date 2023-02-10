package org.mygov.usersservice.services.employee;

import org.mygov.usersservice.etities.Admin;
import org.mygov.usersservice.etities.Employee;
import org.mygov.usersservice.repositoies.AdminRepository;
import org.mygov.usersservice.repositoies.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee loadAdminByEmail(String email){
        return this.employeeRepository.findEmployeeByEmail(email).orElse(null);
    }


}
