package homework.fds.validator;

import homework.fds.log.ActionLog;
import homework.fds.operator.NumberOperator;

import java.util.List;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 2..
 */
public class CountValidator implements RuleValidator {

    private final NumberOperator numberOperator;
    private final Integer threshold;

    public CountValidator(NumberOperator numberOperator, Integer threshold) {
        this.numberOperator = numberOperator;
        this.threshold = threshold;
    }

    @Override
    public boolean valid(List<ActionLog> filteredActionLogList) {
        return numberOperator.operate(filteredActionLogList.size(), threshold);
    }
}
