package homework.fds.core;

import homework.fds.filter.ActionLogFilter;
import homework.fds.log.ActionLog;
import homework.fds.validator.RuleValidator;

import java.util.List;
import java.util.Objects;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 20..
 */
public class Rule {

    private final String name;
    private final ActionLogFilter actionLogFilter;
    private final RuleValidator validator;

    public Rule(String name, ActionLogFilter actionLogFilter, RuleValidator validator) {
        this.name = name;
        this.actionLogFilter = Objects.requireNonNull(actionLogFilter);
        this.validator = Objects.requireNonNull(validator);
    }

    public boolean isMatch(LogRawData logRawData) {
        List<ActionLog> filteredActionLogList = actionLogFilter.doFilter(logRawData);
        return validator.valid(filteredActionLogList);
    }
}
