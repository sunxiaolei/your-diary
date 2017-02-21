package sunxl8.your_diary.ui.diary.list;

import java.util.List;

import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.base.BasePresenter;
import sunxl8.your_diary.db.DbManager;
import sunxl8.your_diary.db.entity.DiaryEntity;

/**
 * Created by sunxl8 on 2017/2/13.
 */

public class DiaryListPresenter extends BasePresenter<DiaryListContract.View> implements DiaryListContract.Presenter {

    protected DiaryListPresenter(BaseActivity activity) {
        super(activity);
    }

    @Override
    public void getDiaryList(Long diaryId) {
        List<DiaryEntity> list = DbManager.queryDiaryListByItemId(diaryId);
        mView.showDiaryList(list);
    }

    @Override
    public void deleteDary(Long id, Long diaryId) {
        DbManager.deleteDiaryById(id);
        editItemEntityCount(diaryId);
        getDiaryList(diaryId);
    }

    private void editItemEntityCount(Long diaryId) {
        DbManager.updateItemCountByItemId(diaryId);
    }
}
