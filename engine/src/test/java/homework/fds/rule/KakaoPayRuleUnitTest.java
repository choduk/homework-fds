package homework.fds.rule;

import homework.fds.filter.Condition;
import homework.fds.log.UserActionLog;
import homework.fds.validator.RuleValidator;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public class KakaoPayRuleUnitTest {

    private Rule rule;
    private Condition condition;
    private RuleValidator validator;

    @Before
    public void setUp() throws Exception {
        validator = Mockito.mock(RuleValidator.class);
        condition = Mockito.mock(Condition.class);
        rule = new KakaoPayRule("Test Rule", condition, validator);
    }

    @Test
    public void return__false__when__userActionLog__is__empty() throws Exception {
        assertThat(rule.isMatching(Collections.emptyList())).isFalse();
    }

    @Test
    public void return__false__when__userActionLog__is__null() throws Exception {
        assertThat(rule.isMatching(null)).isFalse();
    }

    @Test
    public void return__false__when__condition__apply__return__empty() throws Exception {
        // given when
        when(condition.apply(any())).thenReturn(Collections.emptyList());

        // then
        assertThat(rule.isMatching(createMock())).isFalse();
    }

    @Test
    public void return__false__when__validator__return__false() throws Exception {
        // given when
        when(condition.apply(any())).thenReturn(createMock());
        when(validator.validate(any())).thenReturn(false);

        // then
        assertThat(rule.isMatching(createMock())).isFalse();
    }

    @Test
    public void success__when__validator__return__true() throws Exception {
        // given when
        when(condition.apply(any())).thenReturn(createMock());
        when(validator.validate(any())).thenReturn(true);

        // then
        assertThat(rule.isMatching(createMock())).isTrue();
    }

    private List<UserActionLog> createMock() {
        return Lists.newArrayList(UserActionLog.of()
                                               .build());
    }


}