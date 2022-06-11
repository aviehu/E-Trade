package com.workshop.ETrade.Repository;

import com.workshop.ETrade.TestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


//@NoRepositoryBean
public interface MemberRepository extends MongoRepository<TestEntity, String> {

}
