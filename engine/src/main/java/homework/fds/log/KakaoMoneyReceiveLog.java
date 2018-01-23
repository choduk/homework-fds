package homework.fds.log;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 20..
 */
public class KakaoMoneyReceiveLog {

    private String receiverAccount;
    private Long beforeKakaoMoneyBalance;

    private String senderAccount;
    private String senderUserId;

    private Long receivedMoney;

    public KakaoMoneyReceiveLog(Long receivedMoney) {
        this.receivedMoney = receivedMoney;
    }

    public boolean isOverReceivedMoney(long money) {
        return this.receivedMoney >= money;
    }
}
