package sunxl8.your_diary.ui.diary.edit;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.base.BasePresenter;
import sunxl8.your_diary.db.DbManager;
import sunxl8.your_diary.db.entity.DiaryEntity;
import sunxl8.your_diary.db.entity.TagEntity;

/**
 * Created by sunxl8 on 2017/2/13.
 */

public class DiaryEditPresenter extends BasePresenter<DiaryEditContract.View> implements DiaryEditContract.Presenter {

    protected DiaryEditPresenter(BaseActivity activity) {
        super(activity);
    }


    @Override
    public void save(Long diaryId, DiaryEntity entity, List<String> tags) {
        DbManager.addDiary(entity);
        DbManager.updateItemCountByItemId(diaryId);
        mView.saveDone();
        Observable.from(tags)
                .subscribe(s -> {
                    TagEntity tag = new TagEntity();
                    tag.setIdItem(diaryId);
                    tag.setIdDiary(entity.getId());
                    tag.setTag(s);
                    DbManager.addTag(tag);
                });
    }

}
