package homework.fds.extractor;

import homework.fds.log.ActionLog;
import homework.fds.log.ChargeLog;
import homework.fds.log.ReceiveLog;
import homework.fds.log.SendLog;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 5..
 */
public class DefaultLogExtractorTest {
    private ActionLogDataExtractor<Long> extractor;
    private long money;

    @Before
    public void setUp() throws Exception {
        extractor = new DefaultLogExtractor();
        Random r = new Random();
        money = r.nextLong();
    }

    @Test
    public void success__when__extract__charge__log() throws Exception {
        assertThat(extractor.extract(mockCreateChargeLog())).isEqualTo(money);
    }

    @Test
    public void success__when__extract__send__log() throws Exception {
        assertThat(extractor.extract(mockCreateSendLog())).isEqualTo(money);
    }

    @Test
    public void success__when__extract__receive__log() throws Exception {
        assertThat(extractor.extract(mockCreateReceiveLog())).isEqualTo(money);
    }

    private ActionLog mockCreateChargeLog() {
        return create(new ChargeLog(money));
    }

    private ActionLog mockCreateReceiveLog() {
        return create(new ReceiveLog(money));
    }

    private ActionLog mockCreateSendLog() {
        return create(new SendLog(money));
    }

    private <T> ActionLog create(T data) {
        return ActionLog.of()
                            .data(data)
                            .build();
    }
}