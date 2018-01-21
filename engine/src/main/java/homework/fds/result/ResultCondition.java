package homework.fds.result;


import java.util.List;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public interface ResultCondition<T> {
    boolean test(List<T> logs);
}
