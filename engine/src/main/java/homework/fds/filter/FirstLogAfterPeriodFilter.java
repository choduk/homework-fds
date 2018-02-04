package homework.fds.filter;

import homework.fds.core.LogRawData;
import homework.fds.log.ActionLog;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 4..
 */
public class FirstLogAfterPeriodFilter extends AbstractLogFilter {

    private final long hour;

    public FirstLogAfterPeriodFilter(long hour) {
        this.hour = hour;
    }

    @Override
    public LogRawData doFilter(LogRawData logRawData) {
        ActionLog firstLog = logRawData.getActionLogList()
                                       .get(0);

        if (Objects.isNull(firstLog))
            return logRawData;

        LocalDateTime createDt = firstLog.getCreateDt();

        return new PeriodFilter(createDt, createDt.plusHours(hour))
                .doFilter(logRawData);
    }

    @Override
    protected boolean filter(ActionLog actionLog) {
        throw new NotImplementedException();
    }
}
