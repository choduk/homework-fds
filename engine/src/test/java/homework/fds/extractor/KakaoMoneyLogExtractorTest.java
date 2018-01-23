package homework.fds.extractor;

import homework.fds.log.KakaoMoneyChargeLog;
import homework.fds.log.KakaoMoneyReceiveLog;
import homework.fds.log.KakaoMoneySendLog;
import homework.fds.log.UserActionLog;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 24..
 */
public class KakaoMoneyLogExtractorTest {

    private UserActionLogDataExtractor<Long> extractor;
    private long money;

    @Before
    public void setUp() throws Exception {
        extractor = new KakaoMoneyLogExtractor();
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

    private UserActionLog mockCreateChargeLog() {
        return create(new KakaoMoneyChargeLog(money));
    }

    private UserActionLog mockCreateReceiveLog() {
        return create(new KakaoMoneyReceiveLog(money));
    }

    private UserActionLog mockCreateSendLog() {
        return create(new KakaoMoneySendLog(money));
    }

    private <T> UserActionLog create(T data) {
        return UserActionLog.of()
                            .data(data)
                            .build();
    }
}