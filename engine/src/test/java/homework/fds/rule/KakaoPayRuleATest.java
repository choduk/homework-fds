package homework.fds.rule;

import homework.fds.TestHelper;
import homework.fds.extractor.KakaoMoneyLogExtractor;
import homework.fds.extractor.UserActionLogDataExtractor;
import homework.fds.filter.AccountOpenPeriodCondition;
import homework.fds.filter.Condition;
import homework.fds.validator.KakaoMoneyChargeValidator;
import homework.fds.validator.KakaoMoneyLogBalanceCalcValidator;
import homework.fds.validator.KakaoRuleACompositeValidator;
import homework.fds.validator.RuleValidator;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public class KakaoPayRuleATest {

    private Rule rule;

    @Before
    public void setUp() throws Exception {
        rule = new KakaoPayRule("Rule A", createCondition(), createValidator());
    }

    @Test
    public void success() throws Exception {
        // when then
        assertThat(rule.isMatch(TestHelper.getRuleALog())).isTrue();
    }

    private Condition createCondition() {
        return new AccountOpenPeriodCondition(1);
    }

    private RuleValidator createValidator() {
        return new KakaoRuleACompositeValidator();
    }
}