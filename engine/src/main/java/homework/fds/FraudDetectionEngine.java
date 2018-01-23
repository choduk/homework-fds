package homework.fds;

import homework.fds.exception.RuleMatchRuntimeException;
import homework.fds.rule.Rule;
import homework.fds.log.UserActionLog;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

import static java.util.stream.Collectors.toList;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 20..
 */
public class FraudDetectionEngine {

    private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime()
                                                                                        .availableProcessors());
    private final Collection<Rule> rules;

    public FraudDetectionEngine(Collection<Rule> rules) {
        this.rules = rules;
    }

    public List<Rule> findMatchedRules(List<UserActionLog> userActionLogs) {

        List<Future<RuleResult>> futureList = rules.stream()
                                                   .map(rule -> executorService.submit(new RuleCallable(rule, userActionLogs)))
                                                   .collect(toList());

        return futureList.stream()
                         .map(this::ruleMatching)
                         .filter(RuleResult::isSuccess)
                         .map(RuleResult::getRule)
                         .collect(toList());

    }

    private RuleResult ruleMatching(Future<RuleResult> future) {
        try {
            return future.get(2L, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuleMatchRuntimeException(e);
        }
    }

    class RuleCallable implements Callable<RuleResult> {

        final Rule rule;
        final List<UserActionLog> userActionLogs;

        public RuleCallable(Rule rule, List<UserActionLog> userActionLogs) {
            this.rule = rule;
            this.userActionLogs = userActionLogs;
        }

        @Override
        public RuleResult call() throws Exception {
            return new RuleResult(rule, rule.isMatch(userActionLogs));
        }
    }

    class RuleResult {

        final Rule rule;
        final boolean success;

        public RuleResult(Rule rule, boolean success) {
            this.rule = rule;
            this.success = success;
        }

        public boolean isSuccess() {
            return success;
        }

        public Rule getRule() {
            return rule;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        executorService.shutdownNow();
        super.finalize();
    }
}
