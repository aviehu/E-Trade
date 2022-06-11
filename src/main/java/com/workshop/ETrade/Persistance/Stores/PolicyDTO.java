package com.workshop.ETrade.Persistance.Stores;

import com.workshop.ETrade.Domain.Stores.Policies.Policy;
import com.workshop.ETrade.Domain.Stores.Policies.PolicyType;
import com.workshop.ETrade.Domain.Stores.Predicates.Predicate;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class PolicyDTO {
    public String policyOn;
    public String description;
    public PolicyType type;
    @Id
    public String policyId;


    public String operatorType;
    public List<PredicateDTO> predicates;

    public PolicyDTO(){

    }

    public PolicyDTO(String policyOn, String description, String type, String operatorType, List<PredicateDTO> predicates, String policyId) {
        this.policyOn = policyOn;
        this.description = description;
        PolicyType pt;
        switch (type) {
            case "BASKET": {
                pt = PolicyType.BASKET;
                break;
            }
            case "PRODUCT" : {
                pt = PolicyType.PRODUCT;
                break;
            }
            default: {
                pt = PolicyType.CATEGORY;
                break;
            }
        }
        this.type = pt;
        this.operatorType = operatorType;
        this.predicates = predicates;
        this.policyId = policyId;
    }

    public PolicyDTO(Policy policy) {

        policyId = Integer.toString(policy.getId());
        policyOn = policy.getPolicyOn();
        description = policy.getDescription();
        operatorType = policy.getOpType();
        type = policy.getType();
        predicates = new ArrayList<>();
        for(Predicate pre : policy.getPredicates()) {
            predicates.add(pre.init());
        }
    }

}
