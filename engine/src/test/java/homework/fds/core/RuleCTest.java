package homework.fds.core;

import homework.fds.TestHelper;
import homework.fds.filter.ActionTypeFilter;
import homework.fds.filter.FieldFilter;
import homework.fds.filter.PeriodFilter;
import homework.fds.filter.AbstractLogFilter;
import homework.fds.predicate.NumberFieldPredicate;
import homework.fds.type.ActionType;
import homework.fds.type.NumberOperator;
import homework.fds.validator.CountValidator;
import homework.fds.validator.RuleValidator;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 3..
 */
public class RuleCTest {

    private Rule rule;

    @Before
    public void setUp() throws Exception {
        rule = new Rule("Rule C", createRuleFilter(), createRuleValidator());
    }

    @Test
    public void success() throws Exception {
        // when then
        assertThat(rule.isMatch(new LogRawData(TestHelper.getRuleCLog(), LocalDateTime.now()))).isTrue();
    }

    private AbstractLogFilter createRuleFilter() {
        AbstractLogFilter periodFilter = new PeriodFilter(LocalDateTime.now()
                                                                       .minusHours(2L), LocalDateTime.now());
        periodFilter.next(new ActionTypeFilter(ActionType.RECEIVE))
                    .next(new FieldFilter(new NumberFieldPredicate("receivedMoney", NumberOperator.GREATER, 50000L)));
        return periodFilter;
    }

    private RuleValidator createRuleValidator() {
        return new CountValidator(NumberOperator.GREATER, 3);
    }
}