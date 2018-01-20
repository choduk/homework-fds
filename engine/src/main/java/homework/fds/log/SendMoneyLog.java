package homework.fds.log;

import java.time.LocalDateTime;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 20..
 */
public class SendMoneyLog {
    private LocalDateTime createDt;
    private Long userId;

    private String senderAccount;
    private String beforeKakaoMoneyBalance;

    private String receiverAccount;
    private String receiverUserId;

    private Long sendedMoney;
}
