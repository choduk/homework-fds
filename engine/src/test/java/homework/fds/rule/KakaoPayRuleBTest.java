package homework.fds.rule;

import homework.fds.TestHelper;
import homework.fds.filter.AccountOpenPeriodCondition;
import homework.fds.filter.Condition;
import homework.fds.validator.KakaoMoneyReceiveValidator;
import homework.fds.validator.RuleValidator;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public class KakaoPayRuleBTest {

    private Rule rule;

    @Before
    public void setUp() throws Exception {
        rule = new KakaoPayRule("Rule B", createCondition(), createValidator());
    }

    @Test
    public void success() throws Exception {
        // when then
        assertThat(rule.isMatching(TestHelper.getRuleBLog())).isTrue();
    }

    private Condition createCondition() {
        return new AccountOpenPeriodCondition(24 * 7);
    }

    private RuleValidator createValidator() {
        return new KakaoMoneyReceiveValidator(100000L, 5);
    }
}