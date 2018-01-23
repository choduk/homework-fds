package homework.fds.filter;

import homework.fds.log.UserActionLog;
import homework.fds.type.ActionType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public class AccountOpenPeriodCondition extends Condition {

    private final String actionType = ActionType.ACCOUNT_OPEN.name();
    private final int afterHour;

    public AccountOpenPeriodCondition(int afterHour) {
        this.afterHour = afterHour;
    }

    @Override
    protected List<UserActionLog> filter(List<UserActionLog> userActionLogs) {

        LocalDateTime accountOpenDt = findAccountOpenLocalDateTime(userActionLogs);

        Condition periodCondition = new PeriodCondition(accountOpenDt, accountOpenDt.plusHours(afterHour));
        return periodCondition.filter(userActionLogs);
    }

    private LocalDateTime findAccountOpenLocalDateTime(List<UserActionLog> userActionLogs) {
        Optional<UserActionLog> maybeLog = userActionLogs.stream()
                                                         .filter(log -> log.isEqualToActionType(actionType))
                                                         .findFirst();
        return maybeLog.map(UserActionLog::getCreateDt)
                       .orElseThrow(RuntimeException::new);
    }
}
