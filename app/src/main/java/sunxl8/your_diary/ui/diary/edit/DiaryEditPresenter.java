package sunxl8.your_diary.ui.diary.edit;

import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.base.BasePresenter;
import sunxl8.your_diary.ui.diary.calendar.DiaryCalendarContract;

/**
 * Created by sunxl8 on 2017/2/13.
 */

public class DiaryEditPresenter extends BasePresenter<DiaryEditContract.View> implements DiaryEditContract.Presenter {

    protected DiaryEditPresenter(BaseActivity activity) {
        super(activity);
    }
}
