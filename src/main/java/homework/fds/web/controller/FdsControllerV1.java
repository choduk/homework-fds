package homework.fds.web.controller;

import homework.fds.web.controller.dto.FdsResponseV1;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 20..
 */
@RestController
@RequestMapping("v1")
public class FdsControllerV1 {

    @GetMapping("fraud/{user_Id}")
    public FdsResponseV1 getMatchingFraudRules(@PathVariable(name = "user_Id") Long userId) {
        return FdsResponseV1.of()
                            .userId(userId)
                            .build();
    }
}
