package homework.fds.extractor;

import homework.fds.log.ActionLog;
import homework.fds.log.ChargeLog;
import homework.fds.log.ReceiveLog;
import homework.fds.log.SendLog;

import static java.util.Objects.isNull;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 5..
 */
public class DefaultLogExtractor implements ActionLogDataExtractor<Long> {

    @Override
    public Long extract(ActionLog actionLog) {

        if(isNull(actionLog) || isNull(actionLog.getData()))
            return 0L;

        Object data = actionLog.getData();
        if(data instanceof ChargeLog)
            return ChargeLog.class.cast(data).getChargingMoney();

        else if (data instanceof ReceiveLog)
            return ReceiveLog.class.cast(data).getReceivedMoney();

        else if (data instanceof SendLog)
            return SendLog.class.cast(data).getSendedMoney();

        return 0L;
    }
}
