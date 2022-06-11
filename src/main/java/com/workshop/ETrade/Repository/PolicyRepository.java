package com.workshop.ETrade.Repository;

import com.workshop.ETrade.Persistance.Stores.PolicyDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PolicyRepository extends MongoRepository<PolicyDTO, String> {

}
