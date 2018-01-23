package homework.fds.extractor;

import homework.fds.log.KakaoMoneyChargeLog;
import homework.fds.log.KakaoMoneyReceiveLog;
import homework.fds.log.KakaoMoneySendLog;
import homework.fds.log.UserActionLog;

import static java.util.Objects.isNull;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 24..
 */
public class KakaoMoneyLogExtractor implements UserActionLogDataExtractor<Long> {

    @Override
    public Long extract(UserActionLog userActionLog) {

        if(isNull(userActionLog) || isNull(userActionLog.getData()))
            return 0L;

        Object data = userActionLog.getData();
        if(data instanceof KakaoMoneyChargeLog)
            return KakaoMoneyChargeLog.class.cast(data).getChargingMoney();

        else if (data instanceof KakaoMoneyReceiveLog)
            return KakaoMoneyReceiveLog.class.cast(data).getReceivedMoney();

        else if (data instanceof KakaoMoneySendLog)
            return KakaoMoneySendLog.class.cast(data).getSendedMoney();

        return 0L;
    }
}
