package com.ISPBillM.repository;

import com.ISPBillM.entity.BillEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BillRepository extends MongoRepository<BillEntity, String> {
}
