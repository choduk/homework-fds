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

    public static final long QUARTER_MINUTE = 15;
    public static final long HOUR = 60;
    public static final long DAY = HOUR * 24;
    public static final long MONTH = DAY * 30;

    private static List<ActionLog> dummyLog = new ArrayList<>();

    public static List<ActionLog> getAllLog() {
        return dummyLog;
    }

    public static int getActionLogCount(ActionType type) {
        return (int)dummyLog.stream()
                            .filter(log -> log.isEqualToActionType(type.name()))
                            .count();
    }

    public static List<ActionLog> getRuleALog() {
        return getDummyLog(1L);
    }

    public static List<ActionLog> getRuleBLog() {
        return getDummyLog(4L);
    }

    public static List<ActionLog> getRuleCLog() {
        return getDummyLog(5L);
    }

    public static List<ActionLog> getDummyLog(long userId) {
        return dummyLog.stream()
                       .filter(log -> log.getUserId()
                                         .equals(userId))
                       .collect(Collectors.toList());
    }

    static {

        LocalDateTime now = LocalDateTime.now();

        // create account
        dummyLog.add(ActionLog(1L, ACCOUNT_OPEN, now.minusMinutes(MONTH), dataFactory(ACCOUNT_OPEN)));
        dummyLog.add(ActionLog(2L, ACCOUNT_OPEN, now.minusMinutes(MONTH), dataFactory(ACCOUNT_OPEN)));
        dummyLog.add(ActionLog(3L, ACCOUNT_OPEN, now.minusMinutes(MONTH), dataFactory(ACCOUNT_OPEN)));
        dummyLog.add(ActionLog(4L, ACCOUNT_OPEN, now.minusMinutes(MONTH), dataFactory(ACCOUNT_OPEN)));
        dummyLog.add(ActionLog(5L, ACCOUNT_OPEN, now.minusMinutes(MONTH), dataFactory(ACCOUNT_OPEN)));
        dummyLog.add(ActionLog(10L, ACCOUNT_OPEN, now.minusMinutes(MONTH), dataFactory(ACCOUNT_OPEN)));

        // charge log
        dummyLog.add(ActionLog(1L, CHARGE, now.minusMinutes(MONTH - QUARTER_MINUTE), dataFactory(CHARGE)));
        dummyLog.add(ActionLog(2L, CHARGE, now.minusMinutes(MONTH - QUARTER_MINUTE), dataFactory(CHARGE)));
        dummyLog.add(ActionLog(3L, CHARGE, now.minusMinutes(MONTH - QUARTER_MINUTE), dataFactory(CHARGE)));
        dummyLog.add(ActionLog(4L, CHARGE, now.minusMinutes(MONTH - QUARTER_MINUTE), dataFactory(CHARGE)));
        dummyLog.add(ActionLog(5L, CHARGE, now.minusMinutes(MONTH - QUARTER_MINUTE), dataFactory(CHARGE)));

        // rule A target
        dummyLog.add(ActionLog(1L, RECEIVE, now.minusMinutes(MONTH - 5), new ReceiveLog(10000L))); // 0 -> 10,000
        dummyLog.add(ActionLog(1L, SEND, now.minusMinutes(MONTH - 6), new SendLog(5000L)));         // 10,000 -> 5,000
        dummyLog.add(ActionLog(1L, SEND, now.minusMinutes(MONTH - 7), new SendLog(5000L)));         // 5,000 -> 0
//        dummyLog.add(ActionLog(1L, SEND, now.minusMinutes(MONTH - 7), null)); // bug... 무시해야함
        // 위 로그에서 200000L 충전함                                                                                                     // 200,000
        dummyLog.add(ActionLog(1L, RECEIVE, now.minusMinutes(MONTH - QUARTER_MINUTE * 2), new ReceiveLog(500L)));// 200,000 -> 200,500
        dummyLog.add(ActionLog(1L, SEND, now.minusMinutes(MONTH - QUARTER_MINUTE * 2), new SendLog(200000L))); // 200,500 -> 500
        dummyLog.add(ActionLog(1L, RECEIVE, now.minusMinutes(MONTH - QUARTER_MINUTE * 3), new ReceiveLog(9000L)));// 500 -> 9,500

        // rule B target
        dummyLog.add(ActionLog(4L, RECEIVE, now.minusMinutes(MONTH - DAY * 1), dataFactory(RECEIVE)));
        dummyLog.add(ActionLog(4L, RECEIVE, now.minusMinutes(MONTH - DAY * 2), dataFactory(RECEIVE)));
        dummyLog.add(ActionLog(4L, RECEIVE, now.minusMinutes(MONTH - DAY * 3), dataFactory(RECEIVE)));
        dummyLog.add(ActionLog(4L, RECEIVE, now.minusMinutes(MONTH - DAY * 4), dataFactory(RECEIVE)));
        dummyLog.add(ActionLog(4L, RECEIVE, now.minusMinutes(MONTH - DAY * 5), dataFactory(RECEIVE)));
        dummyLog.add(ActionLog(4L, RECEIVE, now.minusMinutes(MONTH - DAY * 6), dataFactory(RECEIVE)));

        // rule C target
        dummyLog.add(ActionLog(5L, RECEIVE, now.minusMinutes(2 * HOUR - QUARTER_MINUTE * 1), dataFactory(RECEIVE)));
        dummyLog.add(ActionLog(5L, RECEIVE, now.minusMinutes(2 * HOUR - QUARTER_MINUTE * 2), dataFactory(RECEIVE)));
        dummyLog.add(ActionLog(5L, RECEIVE, now.minusMinutes(2 * HOUR - QUARTER_MINUTE * 3), dataFactory(RECEIVE)));
        dummyLog.add(ActionLog(5L, RECEIVE, now.minusMinutes(2 * HOUR - QUARTER_MINUTE * 4), dataFactory(RECEIVE)));
        dummyLog.add(ActionLog(5L, RECEIVE, now.minusMinutes(2 * HOUR - QUARTER_MINUTE * 5), dataFactory(RECEIVE)));
    }


    private static <T> ActionLog ActionLog(Long userId, ActionType actionType, LocalDateTime createDt, T data) {
        return ActionLog.of()
                            .userId(userId)
                            .actionType(actionType.name())
                            .createDt(createDt)
                            .data(data)
                            .build();
    }

    private static Object dataFactory(ActionType actionType) {

        switch (actionType) {
            case ACCOUNT_OPEN:
                return new ServiceAccountOpenLog();
            case SEND:
                return new SendLog(100000L);
            case RECEIVE:
                return new ReceiveLog(100000L);
            case CHARGE:
                return new ChargeLog(200000L);
            default:
                return null;
        }

    }
}
