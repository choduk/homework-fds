package homework.fds;

import homework.fds.core.LogRawData;
import homework.fds.log.ActionLog;
import homework.fds.core.Rule;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 20..
 */
public class FraudDetectionEngine {

    private final Collection<Rule> rules;

    public FraudDetectionEngine(Collection<Rule> rules) {
        this.rules = rules;
    }

    public List<Rule> findMatchedRules(List<ActionLog> actionLogList, LocalDateTime now) {

        LogRawData actionLogRawData = new LogRawData(actionLogList, now);

        return rules.stream()
                    .filter(rule -> rule.isMatch(actionLogRawData))
                    .collect(toList());
    }
}
