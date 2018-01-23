package homework.fds.extractor;

import homework.fds.log.UserActionLog;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 24..
 */
public interface UserActionLogDataExtractor<T> {
    T extract(UserActionLog userActionLog);
}
