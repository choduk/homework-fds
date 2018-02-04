package homework.fds.filter;

import homework.fds.TestHelper;
import homework.fds.core.LogRawData;
import homework.fds.log.ActionLog;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 5..
 */
public class PeriodFilterTest {

    private AbstractLogFilter filter;
    private LocalDateTime end;
    private LocalDateTime start;

    @Before
    public void setUp() throws Exception {
        Random r = new Random();
        end = LocalDateTime.now();
        start = end.minusMinutes(r.nextInt() % TestHelper.MONTH);
    }

    @Test
    public void success__when__all__period() throws Exception {

        // when
        filter = new PeriodFilter(start, end);
        List<ActionLog> userActionLogs = filter.doFilter(new LogRawData(TestHelper.getAllLog())).getActionLogList();
        List<ActionLog> dummyActionLogs = filter(start, end);

        // then
        assertThat(userActionLogs.size()).isEqualTo(dummyActionLogs.size());
    }

    private List<ActionLog> filter(LocalDateTime start, LocalDateTime end) {
        return TestHelper.getAllLog().stream()
                         .filter(log -> log.betweenCreateDt(start, end))
                         .collect(Collectors.toList());
    }
}