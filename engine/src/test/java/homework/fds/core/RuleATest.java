package homework.fds.core;

import homework.fds.TestHelper;
import homework.fds.extractor.ActionLogDataExtractor;
import homework.fds.extractor.DefaultLogExtractor;
import homework.fds.filter.*;
import homework.fds.predicate.NumberFieldPredicate;
import homework.fds.predicate.NumberPredicate;
import homework.fds.type.ActionType;
import homework.fds.operator.NumberOperator;
import homework.fds.validator.ChargeAfterBalanceCalcValidator;
import homework.fds.validator.RuleValidator;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 3..
 */
public class RuleATest {

    private Rule rule;

    @Before
    public void setUp() throws Exception {
        rule = new Rule("Rule A", createRuleFilter(), createRuleValidator());
    }

    @Test
    public void success() throws Exception {
        // when
        LogRawData logRawData = new LogRawData(TestHelper.getRuleALog());

        // then
        assertThat(rule.isMatch(logRawData)).isTrue();
    }

    private AbstractLogFilter createRuleFilter() {
        return new FindSkipFilter(ActionType.ACCOUNT_OPEN);
    }

    private RuleValidator createRuleValidator() {

        NumberFieldPredicate chargeMoneyPredicate = new NumberFieldPredicate(ActionType.CHARGE.name(), "chargingMoney", NumberOperator.EQUALS, 200000L);
        NumberPredicate balanceCalcPredicate = new NumberPredicate(NumberOperator.LESS, 1000L);
        ActionLogDataExtractor<Long> logExtractor = new DefaultLogExtractor();

        return new ChargeAfterBalanceCalcValidator(chargeMoneyPredicate, balanceCalcPredicate, logExtractor);
    }
}