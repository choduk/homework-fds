package homework.fds.filter;

import homework.fds.log.ActionLog;

import java.time.LocalDateTime;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 3..
 */
public class PeriodFilter extends ActionLogFilter {

    private final LocalDateTime start;
    private final LocalDateTime end;

    public PeriodFilter(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected boolean filter(ActionLog actionLog) {
        return actionLog.betweenCreateDt(start, end);
    }
}
