package homework.fds.predicate;

import homework.fds.log.ActionLog;
import homework.fds.operator.Operator;

import java.lang.reflect.Field;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 3..
 */
public class FieldPredicate<T> implements ActionLogPredicate {

    private final String fieldName;
    private final T value;
    private final Operator<T> operator;

    public FieldPredicate(String fieldName, T value, Operator<T> operator) {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
    }

    @Override
    public boolean test(ActionLog actionLog) {

        try {
            Object data = actionLog.getData();
            Field targetField = getField(data);

            if (!isEqualsToClass(targetField))
                return false;

            return operator.operate(value, (T) targetField.get(data));

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException();
        }
    }

    private boolean isEqualsToClass(Field targetField) {
        return targetField.getType()
                          .equals(value.getClass());
    }

    private Field getField(Object data) throws NoSuchFieldException {
        Field declaredField = data
                .getClass()
                .getDeclaredField(fieldName);
        declaredField.setAccessible(true);
        return declaredField;
    }
}
