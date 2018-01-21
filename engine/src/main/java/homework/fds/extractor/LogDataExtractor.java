package homework.fds.extractor;

import homework.fds.log.UserActionLog;

import java.util.List;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public interface LogDataExtractor<T> {
    List<T> map(List<UserActionLog> filteredLogs);
}
