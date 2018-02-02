package homework.fds.filter;

import homework.fds.core.LogRawData;
import homework.fds.log.ActionLog;
import homework.fds.predicate.ActionLogPredicate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 2..
 */
public class SimpleRuleFilter implements RuleFilter {

    private List<ActionLogPredicate> predicateList;

    public SimpleRuleFilter(List<ActionLogPredicate> predicateList) {
        this.predicateList = predicateList;
    }

    @Override
    public List<ActionLog> doFilter(LogRawData logRawData) {
        return logRawData.getActionLogStream()
                         .filter(actionLog -> predicateList.stream()
                                                           .allMatch(predicate -> predicate.test(actionLog)))
                         .collect(Collectors.toList());
    }
}
