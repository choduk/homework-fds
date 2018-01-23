package homework.fds;

import homework.fds.log.*;
import homework.fds.type.ActionType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static homework.fds.type.ActionType.*;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 22..
 */
public class TestHelper {

    private static final long QUARTER_HOUR = 15;
    private static final long HOUR = 60;
    private static final long DAY = HOUR * 24;
    private static final long MONTH = DAY * 30;

    private static List<UserActionLog> dummyLog = new ArrayList<>();


    public static List<UserActionLog> getRuleALog() {
        return getDummyLog(1L);
    }

    public static List<UserActionLog> getRuleBLog() {
        return getDummyLog(4L);
    }

    public static List<UserActionLog> getRuleCLog() {
        return getDummyLog(5L);
    }

    public static List<UserActionLog> getDummyLog(long userId) {
        return dummyLog.stream()
                       .filter(log -> log.getUserId().equals(userId))
                       .collect(Collectors.toList());
    }

    static {

        LocalDateTime now = LocalDateTime.now();

        // create account
        dummyLog.add(userActionLog(1L, ACCOUNT_OPEN, now.minusMinutes(MONTH), dataFactory(ACCOUNT_OPEN)));
        dummyLog.add(userActionLog(2L, ACCOUNT_OPEN, now.minusMinutes(MONTH), dataFactory(ACCOUNT_OPEN)));
        dummyLog.add(userActionLog(3L, ACCOUNT_OPEN, now.minusMinutes(MONTH), dataFactory(ACCOUNT_OPEN)));
        dummyLog.add(userActionLog(4L, ACCOUNT_OPEN, now.minusMinutes(MONTH), dataFactory(ACCOUNT_OPEN)));
        dummyLog.add(userActionLog(5L, ACCOUNT_OPEN, now.minusMinutes(MONTH), dataFactory(ACCOUNT_OPEN)));
        dummyLog.add(userActionLog(10L, ACCOUNT_OPEN, now.minusMinutes(MONTH), dataFactory(ACCOUNT_OPEN)));

        // charge log
        dummyLog.add(userActionLog(1L, CHARGE, now.minusMinutes(MONTH - QUARTER_HOUR), dataFactory(CHARGE)));
        dummyLog.add(userActionLog(2L, CHARGE, now.minusMinutes(MONTH - QUARTER_HOUR), dataFactory(CHARGE)));
        dummyLog.add(userActionLog(3L, CHARGE, now.minusMinutes(MONTH - QUARTER_HOUR), dataFactory(CHARGE)));
        dummyLog.add(userActionLog(4L, CHARGE, now.minusMinutes(MONTH - QUARTER_HOUR), dataFactory(CHARGE)));
        dummyLog.add(userActionLog(5L, CHARGE, now.minusMinutes(MONTH - QUARTER_HOUR), dataFactory(CHARGE)));

        // rule A target
        dummyLog.add(userActionLog(1L, SEND, now.minusMinutes(MONTH - QUARTER_HOUR * 2), dataFactory(SEND)));
        dummyLog.add(userActionLog(1L, RECEIVE, now.minusMinutes(MONTH - QUARTER_HOUR * 2), dataFactory(RECEIVE)));
        dummyLog.add(userActionLog(1L, SEND, now.minusMinutes(MONTH - QUARTER_HOUR * 3), dataFactory(SEND)));
        dummyLog.add(userActionLog(1L, SEND, now.minusMinutes(MONTH - QUARTER_HOUR * 3), dataFactory(SEND)));

        // rule B target
        dummyLog.add(userActionLog(4L, RECEIVE, now.minusMinutes(MONTH - DAY * 1), dataFactory(RECEIVE)));
        dummyLog.add(userActionLog(4L, RECEIVE, now.minusMinutes(MONTH - DAY * 2), dataFactory(RECEIVE)));
        dummyLog.add(userActionLog(4L, RECEIVE, now.minusMinutes(MONTH - DAY * 3), dataFactory(RECEIVE)));
        dummyLog.add(userActionLog(4L, RECEIVE, now.minusMinutes(MONTH - DAY * 4), dataFactory(RECEIVE)));
        dummyLog.add(userActionLog(4L, RECEIVE, now.minusMinutes(MONTH - DAY * 5), dataFactory(RECEIVE)));
        dummyLog.add(userActionLog(4L, RECEIVE, now.minusMinutes(MONTH - DAY * 6), dataFactory(RECEIVE)));

        // rule C target
        dummyLog.add(userActionLog(5L, RECEIVE, now.minusMinutes(2 * HOUR - QUARTER_HOUR * 1), dataFactory(RECEIVE)));
        dummyLog.add(userActionLog(5L, RECEIVE, now.minusMinutes(2 * HOUR - QUARTER_HOUR * 2), dataFactory(RECEIVE)));
        dummyLog.add(userActionLog(5L, RECEIVE, now.minusMinutes(2 * HOUR - QUARTER_HOUR * 3), dataFactory(RECEIVE)));
        dummyLog.add(userActionLog(5L, RECEIVE, now.minusMinutes(2 * HOUR - QUARTER_HOUR * 4), dataFactory(RECEIVE)));
        dummyLog.add(userActionLog(5L, RECEIVE, now.minusMinutes(2 * HOUR - QUARTER_HOUR * 5), dataFactory(RECEIVE)));
    }


    private static <T> UserActionLog userActionLog(Long userId, ActionType actionType, LocalDateTime createDt, T data) {
        return UserActionLog.of()
                            .userId(userId)
                            .actionType(actionType.name())
                            .createDt(createDt)
                            .data(data)
                            .build();
    }

    private static Object dataFactory(ActionType actionType) {

        switch(actionType) {
            case ACCOUNT_OPEN:
                return new KakaoMoneyServiceAccountOpenLog();
            case SEND:
                return new KakaoMoneySendLog(100000L);
            case RECEIVE:
                return new KakaoMoneyReceiveLog(100000L);
            case CHARGE:
                return new KakaoMoneyChargeLog(200000L);
            default:
                return null;
        }

    }
}
