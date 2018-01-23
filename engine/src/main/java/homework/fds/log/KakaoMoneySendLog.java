package homework.fds.log;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public class KakaoMoneySendLog {

    private String senderAccount;
    private String beforeKakaoMoneyBalance;

    private String receiverAccount;
    private String receiverUserId;

    private Long sendedMoney;

    public KakaoMoneySendLog(String senderAccount, String beforeKakaoMoneyBalance, String receiverAccount, String receiverUserId, Long sendedMoney) {
        this.senderAccount = senderAccount;
        this.beforeKakaoMoneyBalance = beforeKakaoMoneyBalance;
        this.receiverAccount = receiverAccount;
        this.receiverUserId = receiverUserId;
        this.sendedMoney = sendedMoney;
    }

    public KakaoMoneySendLog(Long sendedMoney) {
        this.sendedMoney = sendedMoney;
    }

    public Long getSendedMoney() {
        return sendedMoney;
    }
}
