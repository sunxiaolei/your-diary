package sunxl8.your_diary.ui.diary.edit;

import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.base.BasePresenter;
import sunxl8.your_diary.db.DbManager;
import sunxl8.your_diary.db.entity.DiaryEntity;

/**
 * Created by sunxl8 on 2017/2/13.
 */

public class DiaryEditPresenter extends BasePresenter<DiaryEditContract.View> implements DiaryEditContract.Presenter {

    protected DiaryEditPresenter(BaseActivity activity) {
        super(activity);
    }


    @Override
    public void save(Long diaryId, DiaryEntity entity) {
        DbManager.addDiary(entity);
        DbManager.updateItemCountByItemId(diaryId);
        mView.saveDone();
    }
}
