package homework.fds.rule;

import homework.fds.TestHelper;
import homework.fds.filter.ActionTypeCondition;
import homework.fds.filter.Condition;
import homework.fds.filter.PeriodCondition;
import homework.fds.type.ActionType;
import homework.fds.validator.KakaoMoneyReceiveValidator;
import homework.fds.validator.RuleValidator;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public class KakaoPayRuleCTest {

    private Rule rule;

    @Before
    public void setUp() throws Exception {
        rule = new KakaoPayRule("Rule C", createCondition(), createValidator());
    }

    @Test
    public void success() throws Exception {
        // when then
        assertThat(rule.isMatch(TestHelper.getRuleCLog())).isTrue();
    }

    private Condition createCondition() {
        Condition periodCondition = new PeriodCondition(LocalDateTime.now()
                                                                     .minusHours(2L), LocalDateTime.now());
        periodCondition.next(new ActionTypeCondition(ActionType.RECEIVE));
        return periodCondition;
    }

    private RuleValidator createValidator() {
        return new KakaoMoneyReceiveValidator(50000L, 3);
    }
}