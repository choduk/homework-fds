package homework.fds.infra.fds.repository;

import homework.fds.infra.fds.entity.KakaoMoneyServiceAccountOpenLog;
import homework.fds.infra.fds.entity.LogId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 24..
 */
@Repository
public interface KakaoMoneyServiceAccountOpenLogJpaRepository extends JpaRepository<KakaoMoneyServiceAccountOpenLog, LogId> {
    List<KakaoMoneyServiceAccountOpenLog> findByIdUserId(Long userId);
}
