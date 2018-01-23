package homework.fds.validator;

import homework.fds.log.KakaoMoneyReceiveLog;
import homework.fds.log.UserActionLog;

import java.util.List;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public class KakaoMoneyReceiveValidator implements RuleValidator {

    private final long thresholdsMoney;
    private final int count;

    public KakaoMoneyReceiveValidator(long thresholdsMoney, int count) {
        this.thresholdsMoney = thresholdsMoney;
        this.count = count;
    }

    @Override
    public boolean validate(List<UserActionLog> userActionLogs) {
        return userActionLogs.stream()
                                  .map(UserActionLog::getData)
                                  .filter(obj -> KakaoMoneyReceiveLog.class.equals(obj.getClass()))
                                  .map(KakaoMoneyReceiveLog.class::cast)
                                  .map(log -> log.isOverReceivedMoney(thresholdsMoney))
                                  .count() >= count;
    }
}
