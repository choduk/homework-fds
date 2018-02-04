package homework.fds.filter;

import homework.fds.TestHelper;
import homework.fds.core.LogRawData;
import homework.fds.log.ActionLog;
import homework.fds.type.ActionType;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author choduk88@sk.com
 * @since 2018. 2. 4..
 */
public class FindSkipFilterTest {

    private AbstractLogFilter skipFilter;

    @Before
    public void setUp() throws Exception {
        skipFilter = new FindSkipFilter(ActionType.SEND);
    }

    @Test
    public void success() throws Exception {
        // given

        // when
        List<ActionLog> dummyLog = TestHelper.getDummyLog(1L);

        LogRawData filteredLogRawData = skipFilter.doFilter(new LogRawData(dummyLog));

        // then
        assertThat(filteredLogRawData.getActionLogList().size()).isEqualTo(6);
    }
}