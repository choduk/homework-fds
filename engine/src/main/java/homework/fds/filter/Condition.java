package homework.fds.filter;

import homework.fds.log.UserActionLog;

import java.util.List;

import static java.util.Objects.isNull;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public abstract class Condition {

    private Condition next;

    public List<UserActionLog> apply(List<UserActionLog> userActionLogs) {

        List<UserActionLog> filteredLogLsist = filter(userActionLogs);
        return isNull(next) ? filteredLogLsist : next.apply(filteredLogLsist);
    }

    protected abstract List<UserActionLog> filter(List<UserActionLog> userActionLogs);
}
