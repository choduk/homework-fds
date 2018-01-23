package homework.fds.web;

import homework.fds.FraudDetectionEngine;
import homework.fds.filter.AccountOpenPeriodCondition;
import homework.fds.filter.ActionTypeCondition;
import homework.fds.filter.Condition;
import homework.fds.filter.PeriodCondition;
import homework.fds.rule.KakaoPayRule;
import homework.fds.rule.Rule;
import homework.fds.type.ActionType;
import homework.fds.validator.KakaoMoneyReceiveValidator;
import homework.fds.validator.KakaoRuleACompositeValidator;
import homework.fds.validator.RuleValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 24..
 */
@Configuration
public class FDSConfig {

    @Bean
    public FraudDetectionEngine fraudDetectionEngine(List<Rule> ruleList) {
        return new FraudDetectionEngine(ruleList);
    }

    @Bean
    public Rule ruleA() {
        Condition condition = new AccountOpenPeriodCondition(1);
        RuleValidator ruleValidator = new KakaoRuleACompositeValidator();
        return new KakaoPayRule("RuleA", condition, ruleValidator);
    }

    @Bean
    public Rule ruleB() {
        Condition condition = new AccountOpenPeriodCondition(24 * 7);
        RuleValidator ruleValidator = new KakaoMoneyReceiveValidator(100000L, 5);
        return new KakaoPayRule("RuleB", condition, ruleValidator);
    }

    @Bean
    public Rule ruleC() {
        Condition periodCondition = new PeriodCondition(LocalDateTime.now()
                                                                     .minusHours(2L), LocalDateTime.now());
        periodCondition.next(new ActionTypeCondition(ActionType.RECEIVE));
        RuleValidator validator = new KakaoMoneyReceiveValidator(50000L, 3);
        return new KakaoPayRule("RuleC", periodCondition, validator);
    }
}
