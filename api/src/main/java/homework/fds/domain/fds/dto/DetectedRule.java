package homework.fds.domain.fds.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 20..
 */
@Getter
@Builder(builderMethodName = "of")
public class DetectedRule {

    private String name;
}
