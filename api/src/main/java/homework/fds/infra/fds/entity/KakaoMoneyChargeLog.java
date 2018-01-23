package homework.fds.infra.fds.entity;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 24..
 */
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 21..
 */
@Data
@Entity
@Table(name = "KAKAO_MONEY_CHARGE_LOG")
public class KakaoMoneyChargeLog implements Serializable {

    @EmbeddedId
    private LogId id;

    @Column(name = "ACCOUNT")
    private String account;

    @Column(name = "CHARGING_MONEY")
    private Long chargingMoney;

    @Column(name = "BANK_ACCOUNT")
    private String bankAccount;
}
