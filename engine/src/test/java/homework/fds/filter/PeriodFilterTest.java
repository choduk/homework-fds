package homework.fds.filter;

import homework.fds.log.UserActionLog;
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
public class PeriodFilterTest {

    private UserActionLogFilter userActionLogFilter;

    @Before
    public void setUp() throws Exception {
        LocalDateTime beforeThreeDay = LocalDateTime.now().minusDays(3L);
        userActionLogFilter = new PeriodFilter(beforeThreeDay, LocalDateTime.now());
    }

    @Test
    public void success() throws Exception {
        // given
        List<UserActionLog> mock = createMock();

        // when then
        List<UserActionLog> passedLogList = userActionLogFilter.apply(mock);
        assertThat(passedLogList.size()).isEqualTo(3);
    }

    private List<UserActionLog> createMock() {
        return Lists.newArrayList(
                UserActionLog.of().createDt(beforeDay(1)).build(),
                UserActionLog.of().createDt(beforeDay(2)).build(),
                UserActionLog.of().createDt(beforeDay(3)).build(),
                UserActionLog.of().createDt(beforeDay(4)).build(),
                UserActionLog.of().createDt(beforeDay(5)).build()
        );
    }

    private LocalDateTime beforeDay(int i) {
        return LocalDateTime.now().minusDays(i);
    }

}