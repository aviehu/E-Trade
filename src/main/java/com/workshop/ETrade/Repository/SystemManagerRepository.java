package com.workshop.ETrade.Repository;

import com.workshop.ETrade.Persistance.Users.SystemManagerDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SystemManagerRepository extends MongoRepository<SystemManagerDTO, String> {
}
