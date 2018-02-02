package homework.fds.filter;

import homework.fds.core.LogRawData;
import homework.fds.log.ActionLog;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 3..
 */
public class PeriodTriggerFilter extends ActionLogFilter {

    private final String startActionType;
    private final int hour;

    public PeriodTriggerFilter(String startActionType, int hour) {
        this.startActionType = Objects.requireNonNull(startActionType);
        this.hour = hour;
    }

    @Override
    public List<ActionLog> doFilter(LogRawData logRawData) {

        Optional<ActionLog> maybeActionLog = logRawData.getActionLogStream()
                                                       .filter(this::filter)
                                                       .findFirst();
        if (!maybeActionLog.isPresent())
            return Collections.emptyList();

        LocalDateTime startDt = maybeActionLog.get()
                                              .getCreateDt();

        return new PeriodFilter(startDt, startDt.plusHours(hour)).doFilter(logRawData);
    }

    @Override
    protected boolean filter(ActionLog actionLog) {
        return actionLog.isEqualToActionType(startActionType);
    }
}
