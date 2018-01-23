package homework.fds.filter;

import homework.fds.TestHelper;
import homework.fds.log.UserActionLog;
import homework.fds.type.ActionType;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author choduk88@sk.com
 * @since 2018. 1. 23..
 */
public class ActionTypeConditionTest {

    private Condition condition;

    @Test
    public void success__when__all__action__log__type() throws Exception {

        // given
        Stream.of(ActionType.values())
              .forEach(actionType -> exceuteTest(actionType));
    }

    private void exceuteTest(ActionType actionType) {
        // given
        condition = new ActionTypeCondition(actionType);

        // when
        List<UserActionLog> userActionLogs = condition.apply(TestHelper.getAllLog());

        // then
        assertThat(userActionLogs).isNotEmpty();
        assertThat(userActionLogs.size()).isEqualTo(TestHelper.getActionLogCount(actionType));
    }
}