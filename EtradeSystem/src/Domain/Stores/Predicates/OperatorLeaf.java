package Domain.Stores.Predicates;

import Domain.Stores.Product;

import java.util.List;
import java.util.Map;

public class OperatorLeaf extends OperatorComponent{

    private List<Predicate> predicates;

    public OperatorLeaf(String type , List<Predicate> predicates) {
        super(type);
        this.predicates = predicates;
    }

    @Override
    public boolean shouldApply(Map<Product, int> amounts) {
        boolean ans = true;
        if(getType().equals("and")) {
            for(Predicate predicate : predicates) {
                ans = ans && predicate.shouldApply(amounts);
            }
        }
        else {
            ans = false;
            for(Predicate predicate : predicates) {
                ans = ans || predicate.shouldApply(amounts);
            }
        }
        return ans;
    }
}
