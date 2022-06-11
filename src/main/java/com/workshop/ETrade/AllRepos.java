package com.workshop.ETrade;

import com.workshop.ETrade.Persistance.Stores.ProductDTO;
import com.workshop.ETrade.Persistance.Stores.StoreDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public class AllRepos {
    private static MongoRepository<StoreDTO, String> storeRepo;
    private static MongoRepository<ProductDTO, String> productRepo;

    public static void setProductRepo(MongoRepository<ProductDTO, String> productRepo) {
        if(AllRepos.productRepo == null) {
            AllRepos.productRepo = productRepo;
        }
    }

    public static MongoRepository<ProductDTO, String> getProductRepo() {
        return productRepo;
    }

    public static void setStoreRepo(MongoRepository<StoreDTO, String> rep) {
        if(storeRepo == null) {
            storeRepo = rep;
        }
    }

    public static MongoRepository<StoreDTO, String> getStoreRepo() {
        return storeRepo;
    }
}
