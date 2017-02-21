package sunxl8.your_diary.ui.diary.calendar;

import java.util.Calendar;
import java.util.List;

import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.base.BasePresenter;
import sunxl8.your_diary.db.DbManager;
import sunxl8.your_diary.db.entity.DiaryEntity;

/**
 * Created by sunxl8 on 2017/2/13.
 */

public class DiaryCalendarPresenter extends BasePresenter<DiaryCalendarContract.View> implements DiaryCalendarContract.Presenter {

    private Calendar mCalendar;

    protected DiaryCalendarPresenter(BaseActivity activity) {
        super(activity);
    }

    @Override
    public void getDiaryList(Long diaryId, Calendar calendar) {
        mCalendar = calendar;
        List<DiaryEntity> list = DbManager.queryDiaryListByDate(diaryId, calendar);
        mView.showDiaryList(list);
    }

    @Override
    public void deleteDary(Long id, Long diaryId) {
        DbManager.deleteDiaryById(id);
        editItemEntityCount(diaryId);
        getDiaryList(diaryId, mCalendar);
    }

    private void editItemEntityCount(Long diaryId) {
        DbManager.updateItemCountByItemId(diaryId);
    }
}
