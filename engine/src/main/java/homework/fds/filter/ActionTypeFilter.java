package homework.fds.filter;

import homework.fds.log.ActionLog;
import homework.fds.type.ActionType;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 4..
 */
public class ActionTypeFilter extends AbstractLogFilter {

    private final ActionType actionType;

    public ActionTypeFilter(ActionType actionType) {
        this.actionType = actionType;
    }

    @Override
    protected boolean filter(ActionLog actionLog) {
        return actionLog.isEqualToActionType(actionType.name());
    }
}
