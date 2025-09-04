package com.ISPBillM.repository;

import com.ISPBillM.entity.EmployeeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<EmployeeEntity, String> {

    Optional<EmployeeEntity> findByUsername(String username);
}
