package homework.fds.infra.fds.repository;

import homework.fds.infra.fds.entity.KakaoMoneyChargeLog;
import homework.fds.infra.fds.entity.LogId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 24..
 */
@Repository
public interface KakaoMoneyChargeLogJpaRepository extends JpaRepository<KakaoMoneyChargeLog, LogId> {
    List<KakaoMoneyChargeLog> findByIdUserId(Long userId);
}
