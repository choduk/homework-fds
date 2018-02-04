package homework.fds.log;

import java.time.LocalDateTime;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 20..
 */
public class ChargeLog {
    private LocalDateTime createDt;
    private Long userId;
    private String account;
    private Long chargingMoney;
    private String bankAccount;

    public ChargeLog(Long chargingMoney) {
        this.chargingMoney = chargingMoney;
    }

    public Long getChargingMoney() {
        return chargingMoney;
    }
}
