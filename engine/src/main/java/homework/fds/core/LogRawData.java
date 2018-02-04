package homework.fds.core;

import homework.fds.log.ActionLog;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 3..
 */
public class LogRawData {

    private final List<ActionLog> actionLogList;
    private final LocalDateTime currentTime;

    public LogRawData(List<ActionLog> actionLogList, LocalDateTime currentTime) {

        this.actionLogList = Optional.of(actionLogList).orElse(Collections.emptyList())
                                     .stream()
                                     .sorted(Comparator.comparing(ActionLog::getCreateDt))
                                     .collect(toList());
        this.currentTime = currentTime;
    }

    public LogRawData(List<ActionLog> actionLogList) {
        this.actionLogList = Optional.of(actionLogList).orElse(Collections.emptyList())
                                     .stream()
                                     .sorted(Comparator.comparing(ActionLog::getCreateDt))
                                     .collect(toList());
        this.currentTime = LocalDateTime.now();
    }

    public List<ActionLog> getActionLogList() {
        return actionLogList;
    }

    public LocalDateTime getCurrentTime() {
        return currentTime;
    }

}
