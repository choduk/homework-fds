package homework.fds.type;

import homework.fds.operator.Operator;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 3..
 */
public enum StringOperator implements Operator<String> {

    STARTS_WITH("startsWith", String::startsWith),
    ENDS_WITH("endsWith", String::endsWith),
    EQUALS("equals", String::equals),
    EQUALS_IGNORE_CASE("equalsIgnoreCase", String::equalsIgnoreCase);

    private final String symbol;
    private final Operator<String> operator;

    StringOperator(String symbol, Operator<String> operator) {
        this.symbol = symbol;
        this.operator = operator;
    }

    @Override
    public boolean operate(String value, String threshold) {
        return this.operator.operate(value, threshold);
    }
}
