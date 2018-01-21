package homework.fds.filter;

import homework.fds.log.UserActionLog;

import java.util.List;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public interface UserActionLogFilter {
    List<UserActionLog> apply(List<UserActionLog> userActionLogs);
}
