package homework.fds.domain.fds.repository;

import homework.fds.log.UserActionLog;

import java.util.List;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 24..
 */
public interface UserActionLogRepository {
    List<UserActionLog> findByUserId(Long userId);
}
