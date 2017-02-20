package sunxl8.your_diary.ui.diary.calendar;

import java.util.Calendar;
import java.util.List;

import sunxl8.your_diary.base.IPresenter;
import sunxl8.your_diary.base.IView;
import sunxl8.your_diary.db.entity.DiaryEntity;

/**
 * Created by sunxl8 on 2017/2/13.
 */

public interface DiaryCalendarContract {

    interface View extends IView {
        void showDiaryList(List<DiaryEntity> list);
    }

    interface Presenter extends IPresenter<View> {

        void getDiaryList(Long diaryId, Calendar calendar);

        void deleteDary(Long id, Long diaryId);
    }
}
