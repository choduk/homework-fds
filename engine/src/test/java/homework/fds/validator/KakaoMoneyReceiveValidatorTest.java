package homework.fds.validator;

import homework.fds.TestHelper;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public class KakaoMoneyReceiveValidatorTest {
    private RuleValidator ruleValidator;

    @Before
    public void setUp() throws Exception {
        ruleValidator = new KakaoMoneyReceiveValidator(50000L, 3);
    }

    @Test
    public void success() throws Exception {
        // when then
        assertThat(ruleValidator.validate(TestHelper.getRuleALog())).isTrue();
        assertThat(ruleValidator.validate(TestHelper.getRuleBLog())).isTrue();
        assertThat(ruleValidator.validate(TestHelper.getRuleCLog())).isTrue();
    }

    @Test
    public void return__false__when__log__is__empty() throws Exception {
        assertThat(ruleValidator.validate(Collections.emptyList())).isFalse();
    }

    @Test
    public void return__false__when__no__received__log() throws Exception {
        // userID 10 -> only account open log
        assertThat(ruleValidator.validate(TestHelper.getDummyLog(10L))).isFalse();
    }

}