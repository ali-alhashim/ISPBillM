package com.ISPBillM.repository;

import com.ISPBillM.entity.BranchEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BranchRepository extends MongoRepository<BranchEntity, String> {
}
