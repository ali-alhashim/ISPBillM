package com.ISPBillM.repository;

import com.ISPBillM.entity.ServiceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServiceRepository extends MongoRepository<ServiceEntity, String> {
}
