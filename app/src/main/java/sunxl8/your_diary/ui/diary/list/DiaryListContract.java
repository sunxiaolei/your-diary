package sunxl8.your_diary.ui.diary.list;

import java.util.List;

import sunxl8.your_diary.base.IPresenter;
import sunxl8.your_diary.base.IView;
import sunxl8.your_diary.db.entity.DiaryEntity;

/**
 * Created by sunxl8 on 2017/2/13.
 */

public interface DiaryListContract {

    interface View extends IView {

        void showDiaryList(List<DiaryEntity> list);

        void showMoreDiaryList(List<DiaryEntity> list);

    }

    interface Presenter extends IPresenter<View> {

        void getDiaryList(Long diaryId);

        void getDiaryList(Long diaryId, int page);

        void deleteDary(Long id, Long diaryId);
    }
}
