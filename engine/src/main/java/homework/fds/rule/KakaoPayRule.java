package homework.fds.rule;

import homework.fds.filter.Condition;
import homework.fds.log.UserActionLog;
import homework.fds.validator.RuleValidator;

import java.util.List;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public class KakaoPayRule implements Rule {

    private String ruleName;
    private Condition condition;
    private RuleValidator validator;

    public KakaoPayRule(String ruleName, Condition condition, RuleValidator validator) {
        this.ruleName = ruleName;
        this.condition = condition;
        this.validator = validator;
    }

    @Override
    public boolean isMatching(List<UserActionLog> userActionLogs) {

        if (isEmpty(userActionLogs))
            return false;

        List<UserActionLog> filteredDataList = condition.apply(userActionLogs);

        if (isEmpty(filteredDataList))
            return false;

        return validator.validate(filteredDataList);
    }

    private boolean isEmpty(List<?> userActionLogs) {
        return userActionLogs == null || userActionLogs.isEmpty();
    }

    @Override
    public String getName() {
        return ruleName;
    }
}
