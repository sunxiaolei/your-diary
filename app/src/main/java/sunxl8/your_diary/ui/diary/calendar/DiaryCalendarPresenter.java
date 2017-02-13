package sunxl8.your_diary.ui.diary.calendar;

import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.base.BasePresenter;
import sunxl8.your_diary.ui.diary.list.DiaryListContract;

/**
 * Created by sunxl8 on 2017/2/13.
 */

public class DiaryCalendarPresenter extends BasePresenter<DiaryCalendarContract.View> implements DiaryCalendarContract.Presenter {

    protected DiaryCalendarPresenter(BaseActivity activity) {
        super(activity);
    }
}
