package homework.fds.infra.fds.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 24..
 */
@Data
@Entity
@Table(name = "KAKAO_MONEY_SEND_LOG")
public class KakaoMoneySendLog implements Serializable {

    @EmbeddedId
    private LogId id;

    @Column(name = "SENDER_ACCOUNT")
    private String senderAccount;

    @Column(name = "BEFORE_KAKAO_MONEY_BALANCE")
    private String beforeKakaoMoneyBalance;

    @Column(name = "RECEIVER_ACCOUNT")
    private String receiverAccount;

    @Column(name = "RECEIVER_USER_ID")
    private String receiverUserId;

    @Column(name = "SENDER_MONEY")
    private Long sendedMoney;
}
