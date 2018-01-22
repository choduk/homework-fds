package homework.fds.validator;

import java.util.List;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public interface RuleValidator<T> {
    boolean validate(List<T> userActionLogStream);
}
