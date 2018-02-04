package homework.fds.extractor;

import homework.fds.log.ActionLog;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 24..
 */
public interface ActionLogDataExtractor<T> {
    T extract(ActionLog actionLog);
}
