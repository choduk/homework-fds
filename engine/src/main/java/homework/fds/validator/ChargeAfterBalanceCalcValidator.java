package homework.fds.validator;

import homework.fds.extractor.ActionLogDataExtractor;
import homework.fds.log.ActionLog;
import homework.fds.log.SendLog;
import homework.fds.predicate.NumberFieldPredicate;
import homework.fds.predicate.NumberPredicate;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 4..
 */
public class ChargeAfterBalanceCalcValidator implements RuleValidator {

    private final NumberFieldPredicate chargeMoneyPredicate;
    private final NumberPredicate balanceCalcPredicate;
    private final ActionLogDataExtractor<Long> logExtractor;

    public ChargeAfterBalanceCalcValidator(NumberFieldPredicate chargeMoneyPredicate, NumberPredicate balanceCalcPredicate, ActionLogDataExtractor<Long> logExtractor) {
        this.chargeMoneyPredicate = Objects.requireNonNull(chargeMoneyPredicate);
        this.balanceCalcPredicate = Objects.requireNonNull(balanceCalcPredicate);
        this.logExtractor = Objects.requireNonNull(logExtractor);
    }

    @Override
    public boolean valid(List<ActionLog> filteredActionLogList) {
        boolean isStart = false;
        long sum = 0;
        for (ActionLog log : filteredActionLogList) {
            if(chargeMoneyPredicate.test(log))
                isStart = true;

            sum += getMoney(log);
            if (isStart && balanceCalcPredicate.test(sum))
                return true;
        }

        return false;
    }

    private Long getMoney(ActionLog userActionLog) {

        Object data = userActionLog.getData();

        if (isNull(data))
            return 0L;

        Long extractedMoney = logExtractor.extract(userActionLog);
        return data instanceof SendLog ?
                extractedMoney * -1 : extractedMoney;
    }
}
