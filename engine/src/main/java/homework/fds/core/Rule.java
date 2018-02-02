package homework.fds.core;

import homework.fds.filter.RuleFilter;
import homework.fds.log.ActionLog;
import homework.fds.validator.RuleValidator;

import java.util.List;
import java.util.Objects;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 20..
 */
public class Rule {

    private final RuleFilter filter;
    private final RuleValidator validator;

    public Rule(RuleFilter filter, RuleValidator validator) {
        this.filter = Objects.requireNonNull(filter);
        this.validator = Objects.requireNonNull(validator);
    }

    public boolean isMatch(LogRawData logRawData) {
        List<ActionLog> filteredActionLogList = filter.doFilter(logRawData);
        return validator.valid(filteredActionLogList);
    }
}
