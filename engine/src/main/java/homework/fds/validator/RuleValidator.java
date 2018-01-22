package homework.fds.validator;

import homework.fds.log.UserActionLog;

import java.util.List;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public interface RuleValidator {
    boolean validate(List<UserActionLog> actionLogs);
}
