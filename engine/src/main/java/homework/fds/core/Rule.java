package homework.fds.core;

import java.time.LocalDateTime;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 20..
 */
public interface Rule {
    boolean isMatch(Long userId, LocalDateTime currentDt);
}
