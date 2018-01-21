package homework.fds.result;


import homework.fds.log.KakaoMoneyReceiveLog;

import java.util.List;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public class KakaoMoneyReceivedResultCondition implements ResultCondition<KakaoMoneyReceiveLog> {

    private final Long receivedMoney;
    private final Long logCount;

    public KakaoMoneyReceivedResultCondition(Long receivedMoney, Long logCount) {
        this.receivedMoney = receivedMoney;
        this.logCount = logCount;
    }

    @Override
    public boolean test(List<KakaoMoneyReceiveLog> logs) {
        return logs.stream()
                   .filter(log -> log.isOverReceivedMoney(receivedMoney))
                   .count() >= logCount;
    }
}
