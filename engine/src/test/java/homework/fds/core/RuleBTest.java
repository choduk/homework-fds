package homework.fds.core;

import homework.fds.TestHelper;
import homework.fds.filter.*;
import homework.fds.predicate.NumberFieldPredicate;
import homework.fds.type.ActionType;
import homework.fds.type.NumberOperator;
import homework.fds.validator.CountValidator;
import homework.fds.validator.RuleValidator;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 3..
 */
public class RuleBTest {

    private Rule rule;

    @Before
    public void setUp() throws Exception {
        rule = new Rule("Rule B", createRuleFilter(), createRuleValidator());
    }

    @Test
    public void success() throws Exception {
        // when
        LogRawData logRawData = new LogRawData(TestHelper.getRuleBLog());

        // then
        assertThat(rule.isMatch(logRawData)).isTrue();
    }

    private AbstractLogFilter createRuleFilter() {

        AbstractLogFilter filter = new FindSkipFilter(ActionType.ACCOUNT_OPEN);
        filter.next(new FirstLogAfterPeriodFilter(24 * 7))
              .next(new ActionTypeFilter(ActionType.RECEIVE))
              .next(new FieldFilter(new NumberFieldPredicate("receivedMoney", NumberOperator.GREATER, 100000L)));
        return filter;
    }

    private RuleValidator createRuleValidator() {
        return new CountValidator(NumberOperator.GREATER, 5);
    }
}