package com.workshop.ETrade;

import com.workshop.ETrade.Persistance.Stores.*;
import com.workshop.ETrade.Persistance.Users.MemberDTO;
import com.workshop.ETrade.Persistance.Users.StoreBasketDTO;
import com.workshop.ETrade.Persistance.Users.SystemManagerDTO;
import com.workshop.ETrade.Repository.StoreBasketRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public class AllRepos {
    private static MongoRepository<StoreDTO, String> storeRepo;
    private static MongoRepository<ProductDTO, String> productRepo;
    private static MongoRepository<MemberDTO, String> memberRepo;
    private static StoreBasketRepository storeBasketRepo;
    private static MongoRepository<BidDTO, String> bidRepo;
    private static MongoRepository<PolicyDTO, String> policyRepo;
    private static MongoRepository<DiscountDTO, String> discountRepo;
    private static MongoRepository<SystemManagerDTO, String> systemManagerRepo;

    public static MongoRepository<DiscountDTO, String> getDiscountRepo() {
        return discountRepo;
    }

    public static void setDiscountRepo(MongoRepository<DiscountDTO, String> discountRepo) {
        if(AllRepos.discountRepo == null) {
            AllRepos.discountRepo = discountRepo;
        }
    }

    public static MongoRepository<PolicyDTO, String> getPolicyRepo() {
        return policyRepo;
    }

    public static void setPolicyRepo(MongoRepository<PolicyDTO, String> policyRepo) {
        if(AllRepos.policyRepo == null) {
            AllRepos.policyRepo = policyRepo;
        }
    }

    public static MongoRepository<BidDTO, String> getBidRepo() {
        return bidRepo;
    }

    public static void setBidRepo(MongoRepository<BidDTO, String> bidRepo) {
        if(AllRepos.bidRepo == null) {
            AllRepos.bidRepo = bidRepo;
        }
    }

    public static MongoRepository<SystemManagerDTO, String> getSystemManagerRepo() {
        return systemManagerRepo;
    }

    public static void setSystemManagerRepo(MongoRepository<SystemManagerDTO, String> systemManagerRepo) {
        if (AllRepos.systemManagerRepo == null)
            AllRepos.systemManagerRepo = systemManagerRepo;
    }

    public static StoreBasketRepository getStoreBasketRepo() {
        return storeBasketRepo;
    }

    public static void setStoreBasketRepo(StoreBasketRepository storeBasketRepo) {
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
