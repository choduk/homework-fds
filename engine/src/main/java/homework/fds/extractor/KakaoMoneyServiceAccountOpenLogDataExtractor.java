package homework.fds.extractor;

import homework.fds.log.KakaoMoneyServiceAccountOpenLog;
import homework.fds.log.UserActionLog;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public class KakaoMoneyServiceAccountOpenLogDataExtractor implements LogDataExtractor<KakaoMoneyServiceAccountOpenLog> {
    @Override
    public List<KakaoMoneyServiceAccountOpenLog> map(List<UserActionLog> filteredLogs) {
        return filteredLogs.stream()
                           .map(UserActionLog::getData)
                           .filter(obj -> obj instanceof KakaoMoneyServiceAccountOpenLog)
                           .map(obj -> (KakaoMoneyServiceAccountOpenLog) obj)
                           .collect(toList());
    }
}
