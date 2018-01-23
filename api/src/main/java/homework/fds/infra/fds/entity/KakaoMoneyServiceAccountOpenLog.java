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
@Table(name = "KAKAO_MONEY_SERVICE_ACCOUNT_OPEN_LOG")
public class KakaoMoneyServiceAccountOpenLog implements Serializable {

    @EmbeddedId
    private LogId id;

    @Column(name = "ACCOUNT")
    private String account;
}
