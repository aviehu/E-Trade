package com.workshop.ETrade.Repository;

import com.workshop.ETrade.Persistance.Users.SystemManagerDTO;
import com.workshop.ETrade.Persistance.Users.TrafficDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;

public interface TrafficRepository extends MongoRepository<TrafficDTO, LocalDate> {
}
