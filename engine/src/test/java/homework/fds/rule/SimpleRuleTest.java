package homework.fds.rule;

import homework.fds.filter.PeriodFilter;
import homework.fds.log.KakaoMoneyChargeLog;
import homework.fds.log.KakaoMoneyReceiveLog;
import homework.fds.log.UserActionLog;
import homework.fds.extractor.KakaoMoneyReceiveLogDataExtractor;
import homework.fds.result.KakaoMoneyReceivedResultCondition;
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
public class SimpleRuleTest {

    private Rule rule;

    @Before
    public void setUp() throws Exception {
        rule = new SimpleRule<>("RuleC",
                new PeriodFilter(LocalDateTime.now().minusDays(7), LocalDateTime.now()),
                new KakaoMoneyReceiveLogDataExtractor(),
                new KakaoMoneyReceivedResultCondition(50000L, 3L));
    }

    @Test
    public void test() throws Exception {
        assertThat(rule.isMatch(createDummy())).isTrue();
    }

    private List<UserActionLog> createDummy() {
        return Lists.newArrayList(
                create(new KakaoMoneyChargeLog()),
                create(new KakaoMoneyReceiveLog(500000L)),
                create(new KakaoMoneyReceiveLog(500000L)),
                create(new KakaoMoneyReceiveLog(500000L)),
                create(new KakaoMoneyReceiveLog(5000L)),
                create(new KakaoMoneyReceiveLog(0L))
        );
    }

    private UserActionLog create(Object data) {
        return UserActionLog.of()
                            .userId(1L)
                            .createDt(LocalDateTime.now().minusDays(3))
                            .actionType(data.getClass()
                                            .getSimpleName())
                            .data(data)
                            .build();
    }
}