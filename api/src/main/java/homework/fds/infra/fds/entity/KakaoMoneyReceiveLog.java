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
@Table(name = "KAKAO_MONEY_RECEIVE_LOG")
public class KakaoMoneyReceiveLog implements Serializable {

    @EmbeddedId
    private LogId id;

    @Column(name = "RECEIVE_ACCOUNT")
    private String receiverAccount;

    @Column(name = "BEFORE_KAKAO_MONEY_BALANCE")
    private Long beforeKakaoMoneyBalance;

    @Column(name = "SENDER_ACCOUNT")
    private String senderAccount;

    @Column(name = "SENDER_USER_ID")
    private String senderUserId;

    @Column(name = "RECEIVE_MONEY")
    private Long receivedMoney;
}
