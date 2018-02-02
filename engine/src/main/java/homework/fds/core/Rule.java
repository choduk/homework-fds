package homework.fds.core;

import homework.fds.log.ActionLog;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 20..
 */
public class Rule {

    private final RuleFilter filter;
    private final RuleValidator validator;
    private Optional<Rule> next = Optional.empty();

    public Rule(RuleFilter filter, RuleValidator validator) {
        this.filter = Objects.requireNonNull(filter);
        this.validator = Objects.requireNonNull(validator);
    }

    public Rule setNext(Rule next) {
        this.next = Optional.of(Objects.requireNonNull(next));
        return next;
    }

    public boolean isMatch(List<ActionLog> actionLogList, LocalDateTime currentDt) {

        List<ActionLog> filteredActionLogList = filter.doFilter(actionLogList, currentDt);

        if(!validator.valid(filteredActionLogList, currentDt))
            return false;

        return !next.isPresent() || next.get().isMatch(actionLogList, currentDt);
    }
}
