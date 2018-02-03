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
public abstract class AbstractLogFilter {

    private Optional<AbstractLogFilter> maybeNextFilter = Optional.empty();

    public LogRawData doFilter(final LogRawData logRawData) {

        List<ActionLog> filteredActionLogList = logRawData.getActionLogList()
                                                          .stream()
                                                          .filter(this::filter)
                                                          .collect(Collectors.toList());
        LogRawData filteredLogRawData = new LogRawData(filteredActionLogList, logRawData.getCurrentTime());

        return maybeNextFilter.map(ruleFilterChain -> ruleFilterChain.doFilter(filteredLogRawData))
                              .orElse(filteredLogRawData);
    }

    public AbstractLogFilter next(AbstractLogFilter next) {
        this.maybeNextFilter = Optional.of(Objects.requireNonNull(next));
        return next;
    }

    protected abstract boolean filter(ActionLog actionLog);
}
