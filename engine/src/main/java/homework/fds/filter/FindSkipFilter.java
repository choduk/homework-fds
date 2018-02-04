package homework.fds.filter;

import homework.fds.core.LogRawData;
import homework.fds.log.ActionLog;
import homework.fds.type.ActionType;

import java.util.List;

/**
 * @author choduk88@sk.com
 * @since 2018. 2. 4..
 */
public class FindSkipFilter extends AbstractLogFilter {

    private final String actionType;

    public FindSkipFilter(String actionType) {
        this.actionType = actionType;
    }

    public FindSkipFilter(ActionType actionType) {
        this.actionType = actionType.name();
    }

    @Override
    public LogRawData doFilter(LogRawData logRawData) {

        List<ActionLog> actionLogList = logRawData.getActionLogList();

        int startIndex = findIndexByFirstActionType(actionLogList);

        if(startIndex <= 0)
            return logRawData;

        return new LogRawData(actionLogList.subList(startIndex, actionLogList.size()), logRawData.getCurrentTime());
    }

    @Override
    protected boolean filter(ActionLog actionLog) {
        return actionLog.isEqualToActionType(actionType);
    }

    private int findIndexByFirstActionType(List<ActionLog> actionLogList) {
        for (int index = 0; index < actionLogList.size(); index++) {
            if (filter(actionLogList.get(index)))
                return index;
        }

        return -1;
    }
}
