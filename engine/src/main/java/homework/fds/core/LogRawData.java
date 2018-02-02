package homework.fds.core;

import homework.fds.log.ActionLog;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 3..
 */
public class LogRawData {

    private final List<ActionLog> actionLogList;
    private final LocalDateTime currentTime;

    public LogRawData(List<ActionLog> actionLogList, LocalDateTime currentTime) {
        this.actionLogList = actionLogList;
        this.currentTime = currentTime;
    }

    public Stream<ActionLog> getActionLogStream() {
        return actionLogList.stream()
                .sorted(Comparator.comparing(ActionLog::getCreateDt));
    }

    public LocalDateTime getCurrentTime() {
        return currentTime;
    }
}
