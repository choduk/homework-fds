package homework.fds.validator;

import homework.fds.extractor.UserActionLogDataExtractor;
import homework.fds.log.KakaoMoneySendLog;
import homework.fds.log.UserActionLog;

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

    public KakaoMoneyLogBalanceCalcValidator(UserActionLogDataExtractor<Long> logExtractor, Predicate<Long> validatePredicate) {
        this.logExtractor = logExtractor;
        this.validatePredicate = validatePredicate;
    }

    @Override
    public boolean validate(List<UserActionLog> userActionLog) {

        long sum = userActionLog.stream()
                                .map(this::getMoney)
                                .mapToLong(Long::longValue)
                                .sum();

        return validatePredicate.test(sum);
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
