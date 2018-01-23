package homework.fds.infra.fds.repository;

import homework.fds.MockDummyLog;
import homework.fds.domain.fds.repository.UserActionLogRepository;
import homework.fds.log.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 24..
 */
@Repository
@RequiredArgsConstructor
public class MockUserActionLogRepositoryImpl implements UserActionLogRepository {

    @Override
    public List<UserActionLog> findByUserId(Long userId) {

        return MockDummyLog.getDummyLog(userId);
    }

}
