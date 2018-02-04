package homework.fds.core;


import homework.fds.filter.AbstractLogFilter;
import homework.fds.log.ActionLog;
import homework.fds.validator.RuleValidator;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 5..
 */
public class RuleTest {

    private Rule rule;
    private AbstractLogFilter logFilter;
    private RuleValidator validator;

    @Before
    public void setUp() throws Exception {
        logFilter = Mockito.mock(AbstractLogFilter.class);
        validator = Mockito.mock(RuleValidator.class);
        rule = new Rule("Test Rule", logFilter, validator);
    }

    @Test
    public void return__false__when__actionLog__is__empty() throws Exception {
        assertThat(rule.isMatch(createEmptyMock())).isFalse();
    }

    @Test
    public void return__false__when__actionLog__is__null() throws Exception {
        assertThat(rule.isMatch(null)).isFalse();
    }

    @Test
    public void return__false__when__condition__apply__return__empty() throws Exception {
        // given when
        when(logFilter.doFilter(any())).thenReturn(createMock());

        // then
        assertThat(rule.isMatch(any())).isFalse();
    }
    
    @Test
    public void success__when__validator__return__true() throws Exception {
        // given when
        when(logFilter.doFilter(any())).thenReturn(createEmptyMock());
        when(validator.valid(any())).thenReturn(true);

        // then
        assertThat(rule.isMatch(createMock())).isTrue();
    }

    private LogRawData createEmptyMock() {
        return new LogRawData(Collections.emptyList(), LocalDateTime.now());
    }

    private LogRawData createMock() {
        return new LogRawData(Lists.newArrayList(ActionLog.of().build()), LocalDateTime.now());
    }
}