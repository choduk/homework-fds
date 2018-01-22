package homework.fds.validator;

import org.junit.Before;
import org.junit.Test;


/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public class KakaoMoneyReceiveValidatorTest {
    private RuleValidator ruleValidator;
    private Object obj;

    @Before
    public void setUp() throws Exception {
        ruleValidator = new KakaoMoneyReceiveValidator(50000L, 3);
    }

    @Test
    public void test() throws Exception {
        // given

        // when

        // then
    }
}