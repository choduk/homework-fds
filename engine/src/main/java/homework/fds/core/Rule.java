package homework.fds.core;

import homework.fds.filter.AbstractLogFilter;
import homework.fds.validator.RuleValidator;

import java.util.Objects;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 20..
 */
public class Rule {

    private final String name;
    private final AbstractLogFilter logFilter;
    private final RuleValidator validator;

    public Rule(String name, AbstractLogFilter logFilter, RuleValidator validator) {
        this.name = name;
        this.logFilter = Objects.requireNonNull(logFilter);
        this.validator = Objects.requireNonNull(validator);
    }

    public boolean isMatch(LogRawData logRawData) {

        if(isEmpty(logRawData))
            return false;

        LogRawData filteredLogRawData = logFilter.doFilter(logRawData);
        return validator.valid(filteredLogRawData.getActionLogList());
    }

    private boolean isEmpty(LogRawData logRawData) {
        return Objects.isNull(logRawData) || logRawData.getActionLogList().isEmpty();
    }
}
