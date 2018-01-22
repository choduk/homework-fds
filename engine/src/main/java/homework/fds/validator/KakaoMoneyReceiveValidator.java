package homework.fds.validator;

import homework.fds.log.KakaoMoneyReceiveLog;

import java.util.List;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public class KakaoMoneyReceiveValidator implements RuleValidator<KakaoMoneyReceiveLog> {

    private final long thresholdsMoney;
    private final int count;

    public KakaoMoneyReceiveValidator(long thresholdsMoney, int count) {
        this.thresholdsMoney = thresholdsMoney;
        this.count = count;
    }

    @Override
    public boolean validate(List<KakaoMoneyReceiveLog> userActionLogStream) {
        return userActionLogStream.stream()
                                  .map(log -> log.isOverReceivedMoney(thresholdsMoney))
                                  .count() >= 3;
    }
}
