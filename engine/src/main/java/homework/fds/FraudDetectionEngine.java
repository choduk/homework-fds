package homework.fds;

import homework.fds.core.Rule;

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

    public List<Rule> findMatchedRules(Long userId) {

        return rules.stream()
                    .filter(rule -> rule.isMatch(userId))
                    .collect(toList());
    }
}
