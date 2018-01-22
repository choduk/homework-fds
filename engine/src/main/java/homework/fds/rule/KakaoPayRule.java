package homework.fds.rule;

import homework.fds.filter.Condition;
import homework.fds.log.UserActionLog;
import homework.fds.validator.RuleValidator;

import java.util.List;

import static java.util.stream.Collectors.toList;


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
    public boolean isMatch(List<UserActionLog> userActionLogs) {

        if (isEmpty(userActionLogs))
            return false;

        List<Object> filteredDataList = condition.apply(userActionLogs)
                                                 .stream()
                                                 .map(UserActionLog::getData)
                                                 .collect(toList());

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
