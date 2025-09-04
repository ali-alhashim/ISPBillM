package com.ISPBillM.repository;

import com.ISPBillM.entity.DepartmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DepartmentRepository extends MongoRepository<DepartmentEntity, String> {
}
