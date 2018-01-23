package homework.fds.rule;

import homework.fds.log.UserActionLog;
import java.util.List;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 20..
 */
public interface Rule {
    boolean isMatching(List<UserActionLog> actionLogs);
    String getName();
}
