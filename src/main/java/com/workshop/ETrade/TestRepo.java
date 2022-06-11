package com.workshop.ETrade;

import com.workshop.ETrade.Persistance.Stores.StoreDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public class TestRepo {
    private static MongoRepository<StoreDTO, String> repo;

    public static void setRepo(MongoRepository<StoreDTO, String> rep) {
        if(repo == null) {
            repo = rep;
        }
    }

    public static MongoRepository<StoreDTO, String> getRepo() {
        return repo;
    }
}
