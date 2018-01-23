package homework.fds.domain.fds.service;

import homework.fds.FraudDetectionEngine;
import homework.fds.domain.fds.dto.FraudRule;
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
    private final UserActionLogRepository userActionLogRepository;

    @Override
    public List<FraudRule> getFraudRules(Long userId) {

        List<UserActionLog> userActionLogs = userActionLogRepository.findByUserId(userId);
        return fraudDetectionEngine.findMatchedRules(userActionLogs)
                                   .stream()
                                   .map(rule -> createFraudRule(rule.getName()))
                                   .collect(Collectors.toList());
    }

    private FraudRule createFraudRule(String ruleName) {
        return FraudRule.of()
                        .name(ruleName)
                        .build();
    }
}
