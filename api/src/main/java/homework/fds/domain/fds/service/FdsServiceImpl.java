package homework.fds.domain.fds.service;

import homework.fds.FraudDetectionEngine;
import homework.fds.domain.fds.dto.DetectedRule;
import homework.fds.domain.fds.repository.UserActionLogRepository;
import homework.fds.log.UserActionLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 20..
 */
@Service
@RequiredArgsConstructor
public class FdsServiceImpl implements FdsService {

    private final FraudDetectionEngine fraudDetectionEngine;

    private final UserActionLogRepository mockUserActionLogRepositoryImpl;

    @Override
    public List<DetectedRule> validate(Long userId) {

        List<UserActionLog> userActionLogs = mockUserActionLogRepositoryImpl.findByUserId(userId);
        return fraudDetectionEngine.ruleMatching(userActionLogs)
                                   .stream()
                                   .map(rule -> createDetectedRule(rule.getName()))
                                   .collect(Collectors.toList());
    }

    private DetectedRule createDetectedRule(String ruleName) {
        return DetectedRule.of()
                           .name(ruleName)
                           .build();
    }
}
