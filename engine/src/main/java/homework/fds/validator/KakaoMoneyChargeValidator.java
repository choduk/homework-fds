package homework.fds.validator;

import homework.fds.log.KakaoMoneyChargeLog;
import homework.fds.log.UserActionLog;

import java.util.List;
import java.util.Objects;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 23..
 */
public class KakaoMoneyChargeValidator implements RuleValidator {

    private final long thresholdsMoney;
    private final int count;

    public KakaoMoneyChargeValidator(long thresholdsMoney, int count) {
        this.thresholdsMoney = thresholdsMoney;
        this.count = count;
    }

    @Override
    public boolean validate(List<UserActionLog> userActionLogs) {
        return userActionLogs.stream()
                                  .map(UserActionLog::getData)
                                  .filter(Objects::nonNull)
                                  .filter(obj -> KakaoMoneyChargeLog.class.equals(obj.getClass()))
                                  .map(KakaoMoneyChargeLog.class::cast)
                                  .map(log -> log.isEqualsToChargeMoney(thresholdsMoney))
                                  .count() >= count;
    }
}
