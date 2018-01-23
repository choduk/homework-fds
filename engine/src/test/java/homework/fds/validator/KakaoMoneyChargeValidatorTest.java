package homework.fds.validator;

import homework.fds.TestHelper;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 23..
 */
public class KakaoMoneyChargeValidatorTest {

    private RuleValidator ruleValidator;

    @Before
    public void setUp() throws Exception {
        ruleValidator = new KakaoMoneyChargeValidator(200000L, 1);
    }

    @Test
    public void success() throws Exception {
        assertThat(ruleValidator.validate(TestHelper.getDummyLog(1L))).isTrue();
    }

    @Test
    public void return__false__when__log__is__empty() throws Exception {
        assertThat(ruleValidator.validate(Collections.emptyList())).isFalse();
    }

    @Test
    public void return__false__when__no__charge__log() throws Exception {
        // userID 10 -> only account open log
        assertThat(ruleValidator.validate(TestHelper.getDummyLog(10L))).isFalse();
    }
}
