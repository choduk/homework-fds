package homework.fds.predicate;

import homework.fds.operator.Operator;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 3..
 */
public class NumberFieldPredicate extends FieldPredicate<Number> {

    public NumberFieldPredicate(String targetField, Operator<Number> operator, Number threshold) {
        super(targetField, operator, threshold);
    }

    public NumberFieldPredicate(String actionType, String targetField, Operator<Number> operator, Number threshold) {
        super(actionType, targetField, operator, threshold);
    }
}
