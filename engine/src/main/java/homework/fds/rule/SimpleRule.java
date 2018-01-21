package homework.fds.rule;

import homework.fds.filter.UserActionLogFilter;
import homework.fds.extractor.LogDataExtractor;
import homework.fds.result.ResultCondition;
import homework.fds.log.UserActionLog;

import java.util.List;

import static java.util.Objects.isNull;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public class SimpleRule<T> implements Rule {

    private String ruleName;
    private UserActionLogFilter userActionLogFilter;
    private LogDataExtractor<T> extractActionData;
    private ResultCondition<T> predicate;

    public SimpleRule(String ruleName, UserActionLogFilter userActionLogFilter, LogDataExtractor<T> logDataExtractor, ResultCondition<T> predicate) {
        this.ruleName = ruleName;
        this.userActionLogFilter = userActionLogFilter;
        this.extractActionData = logDataExtractor;
        this.predicate = predicate;
    }

    public SimpleRule(String ruleName, LogDataExtractor<T> extractActionLogData, ResultCondition<T> predicate) {
        this.ruleName = ruleName;
        this.extractActionData = extractActionLogData;
        this.predicate = predicate;
    }

    @Override
    public boolean isMatch(List<UserActionLog> userActionLogs) {

        if(isEmpty(userActionLogs))
            return false;

        List<UserActionLog> filteredLogs = filtered(userActionLogs);
        return predicate.test(extractActionData.map(filteredLogs));
    }

    private boolean isEmpty(List<UserActionLog> userActionLogs) {
        return userActionLogs == null || userActionLogs.isEmpty();
    }

    private List<UserActionLog> filtered(List<UserActionLog> userActionLogs) {
        return isNull(userActionLogFilter) ?
                userActionLogs : userActionLogFilter.apply(userActionLogs);
    }

    @Override
    public String getName() {
        return ruleName;
    }
}
