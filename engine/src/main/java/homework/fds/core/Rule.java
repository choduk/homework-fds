package homework.fds.core;

import homework.fds.log.ActionLog;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 20..
 */
public interface Rule {
    boolean isMatch(List<ActionLog> actionLogList, LocalDateTime currentDt);
}
