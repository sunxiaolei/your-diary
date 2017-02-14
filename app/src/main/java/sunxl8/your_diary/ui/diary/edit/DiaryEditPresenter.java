package sunxl8.your_diary.ui.diary.edit;

import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.base.BaseApplication;
import sunxl8.your_diary.base.BasePresenter;
import sunxl8.your_diary.db.dao.DaoSession;
import sunxl8.your_diary.db.dao.DiaryEntityDao;
import sunxl8.your_diary.db.entity.DiaryEntity;
import sunxl8.your_diary.ui.diary.calendar.DiaryCalendarContract;

/**
 * Created by sunxl8 on 2017/2/13.
 */

public class DiaryEditPresenter extends BasePresenter<DiaryEditContract.View> implements DiaryEditContract.Presenter {

    private DiaryEntityDao mEntityDao;

    protected DiaryEditPresenter(BaseActivity activity) {
        super(activity);
        DaoSession daoSession = ((BaseApplication) activity.getApplication()).getDaoSession();
        mEntityDao = daoSession.getDiaryEntityDao();
    }

    @Override
    public void save(DiaryEntity entity) {
        mEntityDao.save(entity);
        mView.saveDone();
    }
}
