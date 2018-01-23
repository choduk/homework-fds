package homework.fds.validator;

import homework.fds.extractor.UserActionLogDataExtractor;
import homework.fds.log.KakaoMoneyChargeLog;
import homework.fds.log.UserActionLog;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 24..
 */
public class KakaoMoneyLogBalanceCalcValidatorTest {

    private RuleValidator ruleValidator;
    private UserActionLogDataExtractor<Long> logExtractor;
    private Predicate<Long> validatePredicate = target -> target > 3;

    @Before
    public void setUp() throws Exception {
        logExtractor = Mockito.mock(UserActionLogDataExtractor.class);
        ruleValidator = new KakaoMoneyLogBalanceCalcValidator(logExtractor, validatePredicate);
    }

    @Test
    public void success() throws Exception {
        // when
        // 추출 결과는 무조건 1이라고 가정
        when(logExtractor.extract(any())).thenReturn(1L);

        // then
        assertThat(ruleValidator.validate(createMock(5))).isTrue();
        assertThat(ruleValidator.validate(createMock(3))).isFalse();
    }

    private List<UserActionLog> createMock(int numberOfMock) {
        return IntStream.range(0, numberOfMock)
                        .boxed()
                        .map(i -> UserActionLog.of()
                                               .createDt(LocalDateTime.now())
                                               .data(new KakaoMoneyChargeLog(1L))
                                               .build())
                        .collect(Collectors.toList());
    }
}