package homework.fds.log;

import java.time.LocalDateTime;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 20..
 */
public class ServiceAccountOpenLog {
    private LocalDateTime createDt;
    private Long userId;
    private String account;

    public ServiceAccountOpenLog(Long userId, String account) {
        this.userId = userId;
        this.account = account;
    }
}
