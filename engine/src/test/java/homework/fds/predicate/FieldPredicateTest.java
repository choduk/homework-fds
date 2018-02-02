package homework.fds.predicate;

import homework.fds.log.ActionLog;
import homework.fds.log.ServiceAccountOpenLog;
import homework.fds.type.NumberOperator;
import homework.fds.type.StringOperator;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author choduk88@sk.com
 * @since 2018. 2. 3..
 */
public class FieldPredicateTest {

    private ActionLog actionLog;

    @Before
    public void setUp() throws Exception {
        actionLog = ActionLog.of()
                             .data(new ServiceAccountOpenLog(1L, "Hello World"))
                             .build();
    }

    @Test
    public void test() throws Exception {

        FieldPredicate fieldPredicate = new NumberFieldPredicate("userId", NumberOperator.LESS_THAN, 4L);
        FieldPredicate tmp = new StringFieldPredicate("account", StringOperator.STARTS_WITH, "Hello");
        assertThat(fieldPredicate.test(actionLog)).isTrue();
        assertThat(tmp.test(actionLog)).isTrue();
    }

}