package homework.fds.log;

import java.time.LocalDateTime;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 20..
 */
public class ReceiveLog {
    private LocalDateTime createDt;
    private Long userId;

    private String receiverAccount;
    private String beforeKakaoMoneyBalance;

    private String senderAccount;
    private String senderUserId;

    private Long receivedMoney;

    public ReceiveLog(Long receivedMoney) {
        this.receivedMoney = receivedMoney;
    }
}
