package homework.fds.filter;

import homework.fds.log.ActionLog;
import homework.fds.predicate.ActionLogPredicate;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 4..
 */
public class FieldFilter extends AbstractLogFilter {

    private final ActionLogPredicate actionLogPredicate;

    public FieldFilter(ActionLogPredicate actionLogPredicate) {
        this.actionLogPredicate = actionLogPredicate;
    }

    @Override
    protected boolean filter(ActionLog actionLog) {
        return actionLogPredicate.test(actionLog);
    }
}
