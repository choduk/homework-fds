package homework.fds.infra.fds.repository;

import homework.fds.domain.fds.repository.UserActionLogRepository;
import homework.fds.infra.fds.entity.LogId;
import homework.fds.log.*;
import homework.fds.type.ActionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author choduk88@sk.com
 * @since 2018. 1. 24..
 */
@Repository
@RequiredArgsConstructor
public class UserActionLogRepositoryImpl implements UserActionLogRepository {

    private final KakaoMoneyServiceAccountOpenLogJpaRepository kakaoMoneyServiceAccountOpenLogJpaRepository;
    private final KakaoMoneyReceiveLogJpaRepository kakaoMoneyReceiveLogJpaRepository;
    private final KakaoMoneySendLogJpaRepository kakaoMoneySendLogJpaRepository;
    private final KakaoMoneyChargeLogJpaRepository kakaoMoneyChargeLogJpaRepository;

    @Override
    public List<UserActionLog> findByUserId(Long userId) {

        List<UserActionLog> logs = new ArrayList<>();

        logs.addAll(getServiceAccountOpenLogs(userId));
        logs.addAll(getReceiveLogs(userId));
        logs.addAll(getSendLogs(userId));
        logs.addAll(getChargeLogs(userId));

        return logs;
    }

    private List<UserActionLog> getServiceAccountOpenLogs(Long userId) {
        return kakaoMoneyServiceAccountOpenLogJpaRepository.findByIdUserId(userId)
                                                           .stream()
                                                           .map(entity -> create(entity.getId(), ActionType.ACCOUNT_OPEN, new KakaoMoneyServiceAccountOpenLog(entity.getAccount())))
                                                           .collect(Collectors.toList());
    }

    private List<UserActionLog> getReceiveLogs(Long userId) {
        return kakaoMoneyReceiveLogJpaRepository.findByIdUserId(userId)
                                                .stream()
                                                .map(entity -> create(entity.getId(), ActionType.RECEIVE, new KakaoMoneyReceiveLog(entity.getReceiverAccount(), entity.getBeforeKakaoMoneyBalance(), entity.getSenderAccount(), entity.getSenderUserId(), entity.getReceivedMoney())))
                                                .collect(Collectors.toList());
    }

    private List<UserActionLog> getSendLogs(Long userId) {
        return kakaoMoneySendLogJpaRepository.findByIdUserId(userId)
                                             .stream()
                                             .map(entity -> create(entity.getId(), ActionType.SEND, new KakaoMoneySendLog(entity.getReceiverAccount(), entity.getBeforeKakaoMoneyBalance(), entity.getSenderAccount(), entity.getReceiverUserId(), entity.getSendedMoney())))
                                             .collect(Collectors.toList());
    }

    private List<UserActionLog> getChargeLogs(Long userId) {
        return kakaoMoneyChargeLogJpaRepository.findByIdUserId(userId)
                                               .stream()
                                               .map(entity -> create(entity.getId(), ActionType.CHARGE, new KakaoMoneyChargeLog(entity.getAccount(), entity.getChargingMoney(), entity.getBankAccount())))
                                               .collect(Collectors.toList());
    }

    private UserActionLog create(LogId logId, ActionType actionType, Object data) {
        return UserActionLog.of()
                            .userId(logId.getUserId())
                            .createDt(logId.getCreateDt())
                            .actionType(actionType.name())
                            .data(data)
                            .build();
    }
}
