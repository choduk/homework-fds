package homework.fds.rule;

import homework.fds.log.UserActionLog;
import homework.fds.result.RuleValidator;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public class KakaoPayRuleTest {

    private Rule rule;
    private RuleValidator validator;

    @Before
    public void setUp() throws Exception {
        validator = Mockito.mock(RuleValidator.class);
        rule = new KakaoPayRule("Test Rule", validator);
    }

    @Test
    public void success__return__false__when__userActionLog__is__empty() throws Exception {
        assertThat(rule.isMatch(Collections.emptyList())).isFalse();
    }

    @Test
    public void success__return__false__when__userActionLog__is__null() throws Exception {
        assertThat(rule.isMatch(null)).isFalse();
    }

    @Test
    public void success__when__validator__return__true() throws Exception {
        // given when
        when(validator.validate(any())).thenReturn(true);

        // then
        assertThat(rule.isMatch(createMock())).isTrue();
    }

    @Test
    public void fail__when__validator__return__false() throws Exception {
        // given when
        when(validator.validate(any())).thenReturn(false);

        // then
        assertThat(rule.isMatch(createMock())).isFalse();
    }

    private List<UserActionLog> createMock() {
        return Lists.newArrayList(UserActionLog.of().build());
    }


}