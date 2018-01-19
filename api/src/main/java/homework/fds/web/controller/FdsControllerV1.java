package homework.fds.web.controller;

import homework.fds.domain.fds.service.FdsService;
import homework.fds.domain.fds.dto.FraudRule;
import homework.fds.web.controller.dto.FdsResponseV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.isEmpty;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 20..
 */
@RestController
@RequestMapping("v1")
@RequiredArgsConstructor
public class FdsControllerV1 {

    private final FdsService fdsService;

    @GetMapping("fraud/{user_Id}")
    public FdsResponseV1 getMatchingFraudRules(@PathVariable(name = "user_Id") Long userId) {

        List<FraudRule> fraudRuleList = fdsService.getFraudRules(userId);

        return FdsResponseV1.of()
                            .userId(userId)
                            .fraud(!isEmpty(fraudRuleList))
                            .rule(joinedRule(fraudRuleList))
                            .build();
    }

    private String joinedRule(List<FraudRule> fraudRuleList) {
        if (isEmpty(fraudRuleList))
            return "";
        return fraudRuleList.stream()
                            .map(FraudRule::getName)
                            .collect(Collectors.joining(","));
    }
}
