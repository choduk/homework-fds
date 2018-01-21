package homework.fds.extractor;

import homework.fds.log.KakaoMoneyReceiveLog;
import homework.fds.log.UserActionLog;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public class KakaoMoneyReceiveLogDataExtractor implements LogDataExtractor<KakaoMoneyReceiveLog> {
    @Override
    public List<KakaoMoneyReceiveLog> map(List<UserActionLog> filteredLogs) {
        return filteredLogs.stream()
                           .map(UserActionLog::getData)
                           .filter(obj -> obj instanceof KakaoMoneyReceiveLog)
                           .map(obj -> (KakaoMoneyReceiveLog) obj)
                           .collect(toList());
    }
}
