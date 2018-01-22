package homework.fds.rule;

import homework.fds.filter.AccountOpenPeriodCondition;
import homework.fds.filter.ActionTypeCondition;
import homework.fds.filter.Condition;
import homework.fds.filter.PeriodCondition;
import homework.fds.log.KakaoMoneyReceiveLog;
import homework.fds.log.UserActionLog;
import homework.fds.type.ActionType;
import homework.fds.validator.KakaoMoneyReceiveValidator;
import homework.fds.validator.RuleValidator;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public class KakaoPayRuleIntegrationTest {

    private Rule rule;

    @Before
    public void setUp() throws Exception {
        rule = new KakaoPayRule("Integration Tesgt Rule", createCondition(), createValidator());
    }

    @Test
    public void success() throws Exception {
        // given
        List<UserActionLog> maockActionLogList = createMockActionLogList();

        // when then
        assertThat(rule.isMatch(maockActionLogList)).isTrue();
    }

    private List<UserActionLog> createMockActionLogList() {
        return Lists.newArrayList(createUserActionLog(), createUserActionLog(), createUserActionLog());
    }

    private UserActionLog createUserActionLog() {
        return UserActionLog.of()
                            .actionType(ActionType.RECEIVE.name())
                            .createDt(LocalDateTime.now().minusHours(1))
                            .data(new KakaoMoneyReceiveLog(50000L))
                            .build();
    }

    private Condition createCondition() {
        Condition periodCondition = new PeriodCondition(LocalDateTime.now()
                                                                     .minusHours(2L), LocalDateTime.now());
        periodCondition.next(new ActionTypeCondition(ActionType.RECEIVE));
        return periodCondition;
    }

    private RuleValidator<KakaoMoneyReceiveLog> createValidator() {
        return new KakaoMoneyReceiveValidator(50000L, 3);
    }
}