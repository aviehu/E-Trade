package com.workshop.ETrade.Repository;

import com.workshop.ETrade.Persistance.Users.StoreBasketDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface StoreBasketRepository extends MongoRepository<StoreBasketDTO, String> {

    @Query(value = "{ 'store' : ?0, 'user' : ?1 }")
    StoreBasketDTO findByStoreAndUser(String store, String user);
}
