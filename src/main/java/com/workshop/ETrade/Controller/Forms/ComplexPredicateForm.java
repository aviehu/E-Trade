package com.workshop.ETrade.Controller.Forms;

import com.workshop.ETrade.Domain.Stores.Predicates.OperatorComponent;
import com.workshop.ETrade.Domain.Stores.Predicates.OperatorComposite;

import java.util.ArrayList;
import java.util.List;

public class ComplexPredicateForm implements ComponentPredicateForm{
    public List<ComponentPredicateForm> predicateFormList;
    public String type;

    public ComplexPredicateForm(String t,List<ComponentPredicateForm> predicateForms) {
        predicateFormList = predicateForms;
        type = t;
    }

    @Override
    public OperatorComponent getComponent() {
        List<OperatorComponent> ocs = new ArrayList<>();
        for(ComponentPredicateForm cpf : predicateFormList) {
            ocs.add(cpf.getComponent());
        }
        OperatorComposite oc = new OperatorComposite(type, ocs);
        return oc;
    }
}
