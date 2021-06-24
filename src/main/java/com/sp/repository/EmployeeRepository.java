package com.sp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.sp.entity.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

}
