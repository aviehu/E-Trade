package com.workshop.ETrade.Repository;

import com.workshop.ETrade.Persistance.Stores.BidDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BidRepository extends MongoRepository<BidDTO, String> {
}
