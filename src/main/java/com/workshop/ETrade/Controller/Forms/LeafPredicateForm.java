package com.workshop.ETrade.Controller.Forms;

import com.workshop.ETrade.Domain.Stores.Predicates.OperatorComponent;
import com.workshop.ETrade.Domain.Stores.Predicates.OperatorLeaf;
import com.workshop.ETrade.Domain.Stores.Predicates.Predicate;
import com.workshop.ETrade.Domain.Stores.Predicates.PredicateBuilder;

import java.util.ArrayList;
import java.util.List;

public class LeafPredicateForm implements ComponentPredicateForm{
    public List<PredicateForm> predicateForms;
    public String type;

    public LeafPredicateForm(String t, List<PredicateForm> forms) {
        predicateForms = forms;
        type = t;
    }

    @Override
    public OperatorComponent getComponent() {
        List<Predicate> ps = new ArrayList<>();
        for( PredicateForm pf: predicateForms) {
            switch (pf.predicateType){
                case "amount":
                    ps.add(PredicateBuilder.getProductAmountPredicate(pf.preProduct, (int)pf.minAmount, (int)pf.maxAmount));
                    break;
                case "basket":
                    ps.add(PredicateBuilder.getBasketValuePredicate(pf.minAmount, pf.maxAmount));
                    break;
                case "time":
                    ps.add(PredicateBuilder.getTimePredicate(pf.startTime, pf.endTime, true));
                    break;
                default:
                    break;
            }
        }
        OperatorLeaf ol = new OperatorLeaf(type, ps);
        return ol;
    }


}
