package homework.fds.web.controller;

import homework.fds.web.controller.dto.FdsResponseV1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 24..
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FdsControllerV1IntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void success__return__ruleA() throws Exception {
        FdsResponseV1 result = testRestTemplate.getForObject("/v1/fraud/1", FdsResponseV1.class);
        assertThat(result.getUserId()).isEqualTo(1L);
        assertThat(result.isFraud()).isTrue();
        assertThat(result.getRule()).isEqualTo("RuleA");
    }

    @Test
    public void success__return__ruleB() throws Exception {
        FdsResponseV1 result = testRestTemplate.getForObject("/v1/fraud/4", FdsResponseV1.class);
        assertThat(result.getUserId()).isEqualTo(4L);
        assertThat(result.isFraud()).isTrue();
        assertThat(result.getRule()).isEqualTo("RuleB");
    }

    @Test
    public void success__return__ruleC() throws Exception {
        FdsResponseV1 result = testRestTemplate.getForObject("/v1/fraud/5", FdsResponseV1.class);
        assertThat(result.getUserId()).isEqualTo(5L);
        assertThat(result.isFraud()).isTrue();
        assertThat(result.getRule()).isEqualTo("RuleC");
    }

    @Test
    public void success__return__false() throws Exception {
        FdsResponseV1 result = testRestTemplate.getForObject("/v1/fraud/2", FdsResponseV1.class);
        assertThat(result.getUserId()).isEqualTo(2L);
        assertThat(result.isFraud()).isFalse();
        assertThat(result.getRule()).isEqualTo("");
    }
}