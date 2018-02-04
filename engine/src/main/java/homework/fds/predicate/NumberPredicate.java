package homework.fds.predicate;

import homework.fds.type.NumberOperator;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 5..
 */
public class NumberPredicate implements Predicate<Number> {

    private final NumberOperator numberOperator;
    private final Number threshold;

    public NumberPredicate(NumberOperator numberOperator, Number threshold) {
        this.numberOperator = Objects.requireNonNull(numberOperator);
        this.threshold = Objects.requireNonNull(threshold);
    }

    @Override
    public boolean test(Number target) {
        return numberOperator.operate(target, threshold);
    }
}
