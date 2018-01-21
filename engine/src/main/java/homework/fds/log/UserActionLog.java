package homework.fds.log;

import java.time.LocalDateTime;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public class UserActionLog<T> {
    private Long userId;
    private LocalDateTime createDt;
    private String actionType;
    private T data;

    public static UserActionLogBuilder of() {
        return new UserActionLogBuilder();
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

    public static final class UserActionLogBuilder<T> {
        private Long userId;
        private LocalDateTime createDt;
        private String actionType;
        private T data;

        private UserActionLogBuilder() {
        }

        public UserActionLogBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public UserActionLogBuilder createDt(LocalDateTime createDt) {
            this.createDt = createDt;
            return this;
        }

        public UserActionLogBuilder actionType(String eventType) {
            this.actionType = eventType;
            return this;
        }

        public UserActionLogBuilder data(T data) {
            this.data = data;
            return this;
        }

        public UserActionLog build() {
            UserActionLog userActionLog = new UserActionLog();
            userActionLog.createDt = this.createDt;
            userActionLog.data = this.data;
            userActionLog.actionType = this.actionType;
            userActionLog.userId = this.userId;
            return userActionLog;
        }
    }
}
