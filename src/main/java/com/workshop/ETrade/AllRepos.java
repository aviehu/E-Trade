package com.workshop.ETrade;

import com.workshop.ETrade.Persistance.Stores.ProductDTO;
import com.workshop.ETrade.Persistance.Stores.StoreDTO;
import com.workshop.ETrade.Persistance.Users.MemberDTO;
import com.workshop.ETrade.Persistance.Users.StoreBasketDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public class AllRepos {
    private static MongoRepository<StoreDTO, String> storeRepo;
    private static MongoRepository<ProductDTO, String> productRepo;
    private static MongoRepository<MemberDTO, String> memberRepo;
    private static MongoRepository<StoreBasketDTO, String> storeBasketRepo;

    public static MongoRepository<StoreBasketDTO, String> getStoreBasketRepo() {
        return storeBasketRepo;
    }

    public static void setStoreBasketRepo(MongoRepository<StoreBasketDTO, String> storeBasketRepo) {
        AllRepos.storeBasketRepo = storeBasketRepo;
    }

    public static MongoRepository<MemberDTO, String> getMemberRepo() {
        return memberRepo;
    }

    public static void setMemberRepo(MongoRepository<MemberDTO, String> memberRepo) {
        if(AllRepos.memberRepo == null)
            AllRepos.memberRepo = memberRepo;
    }

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
