package homework.fds.web.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 20..
 */
@Data
@Builder(builderMethodName = "of")
public class FdsResponseV1 implements Serializable {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("is_fraud")
    private boolean fraud;

    private String rule;
}
