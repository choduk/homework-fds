package homework.fds.filter;

import homework.fds.core.LogRawData;
import homework.fds.log.ActionLog;

import java.util.List;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 2..
 */
public interface RuleFilter {
    List<ActionLog> doFilter(LogRawData logRawData);
}
