package homework.fds.filter;

import homework.fds.core.LogRawData;
import homework.fds.log.ActionLog;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 2..
 */
public abstract class ActionLogFilter {

    private Optional<ActionLogFilter> maybeNextFilter = Optional.empty();

    public List<ActionLog> doFilter(final LogRawData logRawData) {

        final List<ActionLog> filteredActionLogList = logRawData.getActionLogStream()
                                                                .filter(this::filter)
                                                                .collect(Collectors.toList());

        return maybeNextFilter.map(ruleFilterChain -> ruleFilterChain.doFilter(new LogRawData(filteredActionLogList, logRawData.getCurrentTime())))
                              .orElse(filteredActionLogList);
    }

    public ActionLogFilter next(ActionLogFilter next) {
        this.maybeNextFilter = Optional.of(Objects.requireNonNull(next));
        return next;
    }

    protected abstract boolean filter(ActionLog actionLog);
}
