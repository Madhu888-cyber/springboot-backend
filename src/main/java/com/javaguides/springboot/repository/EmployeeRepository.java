package com.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaguides.springboot.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
