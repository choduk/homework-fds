package homework.fds.extractor;

import homework.fds.log.KakaoMoneyChargeLog;
import homework.fds.log.KakaoMoneyReceiveLog;
import homework.fds.log.UserActionLog;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public class KakaoMoneyChargeLogDataExtractor implements LogDataExtractor<KakaoMoneyChargeLog> {
    @Override
    public List<KakaoMoneyChargeLog> map(List<UserActionLog> filteredLogs) {
        return filteredLogs.stream()
                           .map(UserActionLog::getData)
                           .filter(obj -> obj instanceof KakaoMoneyChargeLog)
                           .map(obj -> (KakaoMoneyChargeLog) obj)
                           .collect(toList());
    }
}
