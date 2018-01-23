package homework.fds.infra.fds.entity;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 24..
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 24..
 */
@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class LogId implements Serializable {

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "CREATE_DT")
    private LocalDateTime createDt;
}

