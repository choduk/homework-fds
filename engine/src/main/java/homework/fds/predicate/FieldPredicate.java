package homework.fds.predicate;

import homework.fds.log.ActionLog;
import homework.fds.operator.Operator;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 3..
 */
public class FieldPredicate<T> implements ActionLogPredicate {

    private final String targetField;
    private final Operator<T> operator;
    private final T threshold;

    public FieldPredicate(String targetField, Operator<T> operator, T threshold) {
        this.targetField = Objects.requireNonNull(targetField);
        this.operator = Objects.requireNonNull(operator);
        this.threshold = Objects.requireNonNull(threshold);
    }

    @Override
    public boolean test(ActionLog actionLog) {

        try {
            Object data = actionLog.getData();
            Field targetField = getField(data);

            if (!isEqualsToClass(targetField))
                throw new RuntimeException();

            return operator.operate((T) targetField.get(data), threshold);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException();
        }
    }

    private boolean isEqualsToClass(Field targetField) {
        return targetField.getType()
                          .equals(threshold.getClass());
    }

    private Field getField(Object data) throws NoSuchFieldException {
        Field declaredField = data
                .getClass()
                .getDeclaredField(targetField);
        declaredField.setAccessible(true);
        return declaredField;
    }
}
