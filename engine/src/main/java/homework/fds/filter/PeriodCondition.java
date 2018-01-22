package homework.fds.filter;

import homework.fds.log.UserActionLog;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public class PeriodCondition extends Condition {

    private final LocalDateTime start;
    private final LocalDateTime end;

    public PeriodCondition(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected List<UserActionLog> filter(List<UserActionLog> userActionLogs) {
        return userActionLogs.stream()
                             .filter(log -> log.betweenCreateDt(start, end))
                             .collect(Collectors.toList());
    }
}
