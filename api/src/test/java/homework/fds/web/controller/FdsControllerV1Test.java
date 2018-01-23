package homework.fds.web.controller;

import homework.fds.domain.fds.service.FdsService;
import homework.fds.domain.fds.dto.DetectedRule;
import homework.fds.web.controller.dto.FdsResponseV1;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 20..
 */
@RunWith(SpringRunner.class)
public class FdsControllerV1Test {

    private FdsControllerV1 fdsControllerV1;
    private FdsService mockService;

    @Before
    public void setUp() throws Exception {
        mockService = Mockito.mock(FdsService.class);
        fdsControllerV1 = new FdsControllerV1(mockService);
    }

    @Test
    public void not__fraud__when__service__return__empty__rules() throws Exception {
        // given when
        when(mockService.validate(anyLong())).thenReturn(Collections.emptyList());
        FdsResponseV1 response = fdsControllerV1.getMatchingFraudRules(anyLong());

        // then
        assertThat(response.isFraud()).isFalse();
        assertThat(response.getRule()).isEmpty();
    }

    @Test
    public void fraud__when__service__return__fraud__rules() throws Exception {
        // given when
        when(mockService.validate(anyLong())).thenReturn(Lists.newArrayList(
                createMockFraudRule("rule1"),
                createMockFraudRule("rule2")
        ));
        FdsResponseV1 response = fdsControllerV1.getMatchingFraudRules(anyLong());

        // then
        assertThat(response.isFraud()).isTrue();
        assertThat(response.getRule()).isEqualTo("rule1,rule2");
    }

    private DetectedRule createMockFraudRule(String ruleName) {
        return DetectedRule.of()
                           .name(ruleName)
                           .build();
    }
}