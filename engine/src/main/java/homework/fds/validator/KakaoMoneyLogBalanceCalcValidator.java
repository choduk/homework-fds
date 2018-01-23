package homework.fds.validator;

import homework.fds.extractor.UserActionLogDataExtractor;
import homework.fds.log.KakaoMoneySendLog;
import homework.fds.log.UserActionLog;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Objects.isNull;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 23..
 */
public class KakaoMoneyLogBalanceCalcValidator implements RuleValidator {

    private final UserActionLogDataExtractor<Long> logExtractor;
    private final Predicate<Long> validatePredicate;
    private Predicate<Long> validateStartAfterTargetPredicate;

    public KakaoMoneyLogBalanceCalcValidator(UserActionLogDataExtractor<Long> logExtractor, Predicate<Long> validatePredicate) {
        this.logExtractor = logExtractor;
        this.validatePredicate = validatePredicate;
    }

    public KakaoMoneyLogBalanceCalcValidator(UserActionLogDataExtractor<Long> logExtractor, Predicate<Long> validatePredicate, Predicate<Long> validateStartAfterTargetPredicate) {
        this.logExtractor = logExtractor;
        this.validatePredicate = validatePredicate;
        this.validateStartAfterTargetPredicate = validateStartAfterTargetPredicate;
    }

    @Override
    public boolean validate(List<UserActionLog> userActionLogs) {

        userActionLogs.sort(Comparator.comparing(UserActionLog::getCreateDt));

        boolean isStart = false;
        long sum = 0;
        for (UserActionLog log : userActionLogs) {
            Long money = getMoney(log);
            if(isValidateStart(money))
                isStart = true;

            sum += money;
            if (isStart && validatePredicate.test(sum))
                return true;
        }

        return false;

//        long sum = userActionLogs.stream()
//                                .map(this::getMoney)
//                                .mapToLong(Long::longValue)
//                                .sum();
//        return validatePredicate.test(sum);
    }

    private boolean isValidateStart(Long money) {
        return isNull(validateStartAfterTargetPredicate) || validateStartAfterTargetPredicate.test(money);
    }

    private Long getMoney(UserActionLog userActionLog) {

        Object data = userActionLog.getData();

        if (isNull(data))
            return 0L;

        Long extractedMoney = logExtractor.extract(userActionLog);
        return data instanceof KakaoMoneySendLog ?
                extractedMoney * -1 : extractedMoney;
    }
}
