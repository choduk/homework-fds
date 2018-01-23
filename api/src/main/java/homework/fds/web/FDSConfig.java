package homework.fds.web;

import homework.fds.FraudDetectionEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 24..
 */
@Configuration
public class FDSConfig {

    @Bean
    public FraudDetectionEngine fraudDetectionEngine() {
        return new FraudDetectionEngine(Collections.emptyList());
    }
}
