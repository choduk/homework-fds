package homework.fds.log;

import java.time.LocalDateTime;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 2..
 */
public class ActionLog<T> {
    private Long userId;
    private LocalDateTime createDt;
    private String actionType;
    private T data;

    public static ActionLogBuilder of() {
        return new ActionLogBuilder();
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDateTime getCreateDt() {
        return createDt;
    }

    public boolean isEqualToActionType(String actionType) {
        return this.actionType.equals(actionType);
    }

    public boolean betweenCreateDt(LocalDateTime start, LocalDateTime end) {
        return createDt.isAfter(start) && createDt.isBefore(end);
    }

    public T getData() {
        return data;
    }

    public boolean isTargetClass(Class clazz) {
        return clazz.equals(data.getClass());
    }

    public static final class ActionLogBuilder<T> {
        private Long userId;
        private LocalDateTime createDt;
        private String actionType;
        private T data;

        private ActionLogBuilder() {
        }

        public ActionLogBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public ActionLogBuilder createDt(LocalDateTime createDt) {
            this.createDt = createDt;
            return this;
        }

        public ActionLogBuilder actionType(String eventType) {
            this.actionType = eventType;
            return this;
        }

        public ActionLogBuilder data(T data) {
            this.data = data;
            return this;
        }

        public ActionLog build() {
            ActionLog userActionLog = new ActionLog();
            userActionLog.createDt = this.createDt;
            userActionLog.data = this.data;
            userActionLog.actionType = this.actionType;
            userActionLog.userId = this.userId;
            return userActionLog;
        }
    }
}
