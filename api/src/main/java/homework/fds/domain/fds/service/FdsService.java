package homework.fds.domain.fds.service;

import homework.fds.domain.fds.dto.FraudRule;

import java.util.List;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 20..
 */
public interface FdsService {
    List<FraudRule> getFraudRules(Long userId);
}
