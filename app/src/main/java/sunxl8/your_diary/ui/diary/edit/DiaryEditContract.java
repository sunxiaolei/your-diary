package sunxl8.your_diary.ui.diary.edit;

import sunxl8.your_diary.base.IPresenter;
import sunxl8.your_diary.base.IView;
import sunxl8.your_diary.db.entity.DiaryEntity;

/**
 * Created by sunxl8 on 2017/2/13.
 */

public interface DiaryEditContract {

    interface View extends IView {
        void saveDone();
    }

    interface Presenter extends IPresenter<View> {
        void save(Long diaryId, DiaryEntity entity);
    }
}
