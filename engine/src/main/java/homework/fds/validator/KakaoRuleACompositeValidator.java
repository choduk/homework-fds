package homework.fds.validator;

import homework.fds.extractor.KakaoMoneyLogExtractor;
import homework.fds.log.UserActionLog;

import java.util.List;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 24..
 */
public class KakaoRuleACompositeValidator implements RuleValidator {

    private final RuleValidator chargeValidator;
    private final RuleValidator balanceCalcValidator;

    public KakaoRuleACompositeValidator() {
        chargeValidator = new KakaoMoneyChargeValidator(200000L, 1);
        balanceCalcValidator = new KakaoMoneyLogBalanceCalcValidator(new KakaoMoneyLogExtractor(), result -> result <= 1000L);
    }

    @Override
    public boolean validate(List<UserActionLog> userActionLog) {

        if (!chargeValidator.validate(userActionLog))
            return false;

        return balanceCalcValidator.validate(userActionLog);
    }
}
