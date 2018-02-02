package homework.fds.type;

import homework.fds.operator.Operator;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 3..
 */
public enum NumberOperator implements Operator<Number> {

    LESS("<=", (o1, o2) -> o1.doubleValue() <= o2.doubleValue()),
    LESS_THAN("<", (o1, o2) -> o1.doubleValue() < o2.doubleValue()),

    GREATER(">=", (o1, o2) -> o1.doubleValue() >= o2.doubleValue()),
    GREATER_THAN(">", (o1, o2) -> o1.doubleValue() > o2.doubleValue()),

    EQUALS("==", (o1, o2) -> o1.doubleValue() == o2.doubleValue());

    private final String symbol;
    private final Operator<Number> operator;

    NumberOperator(String symbol, Operator<Number> operator) {
        this.symbol = symbol;
        this.operator = operator;
    }

    @Override
    public boolean operate(Number target, Number threshold) {
        return operator.operate(target, threshold);
    }
}
