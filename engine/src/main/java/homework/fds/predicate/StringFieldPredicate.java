package homework.fds.predicate;

import homework.fds.operator.Operator;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 3..
 */
public class StringFieldPredicate extends FieldPredicate<String> {
    public StringFieldPredicate(String targetField, Operator<String> operator, String threshold) {
        super(targetField, operator, threshold);
    }
}
