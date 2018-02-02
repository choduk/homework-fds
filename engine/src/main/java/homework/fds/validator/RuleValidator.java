package homework.fds.validator;

import homework.fds.log.ActionLog;

import java.util.List;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 2..
 */
public interface RuleValidator {
    boolean valid(List<ActionLog> filteredActionLogList);
}
