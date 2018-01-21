package homework.fds.filter;

import homework.fds.log.UserActionLog;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public class PeriodFilter implements UserActionLogFilter {

    private final LocalDateTime start;
    private final LocalDateTime end;

    public PeriodFilter(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public List<UserActionLog> apply(List<UserActionLog> userActionLogs) {
        return userActionLogs.stream()
                             .filter(log -> log.betweenCreateDt(start, end))
                             .collect(Collectors.toList());
    }
}
