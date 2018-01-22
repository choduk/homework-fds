package homework.fds.filter;

import homework.fds.log.UserActionLog;
import homework.fds.type.ActionType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public class ActionTypeCondition extends Condition {

    private final String actionType;

    public ActionTypeCondition(String actionType) {
        this.actionType = actionType;
    }

    public ActionTypeCondition(ActionType actionType) {
        this.actionType = actionType.name();
    }

    @Override
    protected List<UserActionLog> filter(List<UserActionLog> userActionLogs) {
        return userActionLogs.stream()
                .filter(log -> log.isEqualToActionType(actionType))
                .collect(Collectors.toList());
    }
}
