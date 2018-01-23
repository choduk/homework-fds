package homework.fds.log;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 20..
 */
public class KakaoMoneyChargeLog {
    private String account;
    private Long chargingMoney;
    private String bankAccount;

    public KakaoMoneyChargeLog(Long chargingMoney) {
        this.chargingMoney = chargingMoney;
    }

    public boolean isEqualsToChargeMoney(long thresholdsMoney) {
        return thresholdsMoney == chargingMoney;
    }

    public Long getChargingMoney() {
        return chargingMoney;
    }
}
