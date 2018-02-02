package homework.fds.filter;

import homework.fds.core.LogRawData;
import homework.fds.log.ActionLog;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 3..
 */
public class PeriodFilter implements RuleFilter {

    private final LocalDateTime start;
    private final LocalDateTime end;

    public PeriodFilter(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public List<ActionLog> doFilter(LogRawData logRawData) {
        return logRawData.getActionLogStream()
                .filter(actionLog -> actionLog.betweenCreateDt(start, end))
                .collect(Collectors.toList());
    }
}
