package com.service;


import com.domain.Employee;
import com.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository =employeeRepository ;
    }
    @Transactional
    public boolean insert(Employee employee) {
//        employee.setFirst_name(employee.getFirst_name());
//        employee.setLast_name(employee.getLast_name());
//        employee.setGender(employee.getGender());
//        employee.setJoining_date(employee.getJoining_date());
//        employee.setStatus(employee.getStatus());
        return employeeRepository.create(employee);
    }

    @Transactional(readOnly = true)
    public Employee get(Long employee_id) {
        return employeeRepository.get(employee_id);
    }

}
