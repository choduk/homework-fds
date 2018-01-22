package homework.fds.rule;

import homework.fds.log.UserActionLog;
import homework.fds.result.RuleValidator;

import java.util.List;


/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public class KakaoPayRule implements Rule {

    private String ruleName;
    private RuleValidator validator;

    public KakaoPayRule(String ruleName, RuleValidator validator) {
        this.ruleName = ruleName;
        this.validator = validator;
    }

    @Override
    public boolean isMatch(List<UserActionLog> userActionLogs) {

        if(isEmpty(userActionLogs))
            return false;

        return validator.validate(userActionLogs);
    }

    private boolean isEmpty(List<UserActionLog> userActionLogs) {
        return userActionLogs == null || userActionLogs.isEmpty();
    }

    @Override
    public String getName() {
        return ruleName;
    }
}
