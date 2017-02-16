package sunxl8.your_diary.ui.diary.edit;

import org.greenrobot.greendao.query.Query;

import java.util.List;

import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.base.BaseApplication;
import sunxl8.your_diary.base.BasePresenter;
import sunxl8.your_diary.db.dao.DaoSession;
import sunxl8.your_diary.db.dao.DiaryEntityDao;
import sunxl8.your_diary.db.dao.ItemEntityDao;
import sunxl8.your_diary.db.entity.DiaryEntity;
import sunxl8.your_diary.db.entity.ItemEntity;
import sunxl8.your_diary.event.MainRefreshEvent;
import sunxl8.your_diary.ui.diary.calendar.DiaryCalendarContract;
import sunxl8.your_diary.util.RxBus;

/**
 * Created by sunxl8 on 2017/2/13.
 */

public class DiaryEditPresenter extends BasePresenter<DiaryEditContract.View> implements DiaryEditContract.Presenter {

    private DiaryEntityDao mEntityDao;
    private ItemEntityDao entityItemDao;

    protected DiaryEditPresenter(BaseActivity activity) {
        super(activity);
        DaoSession daoSession = ((BaseApplication) activity.getApplication()).getDaoSession();
        mEntityDao = daoSession.getDiaryEntityDao();
        entityItemDao = daoSession.getItemEntityDao();
    }

    private void editItemEntityCount(Long diaryId) {
        Query query = mEntityDao.queryBuilder()
                .where(DiaryEntityDao.Properties.DiaryId.eq(diaryId))
                .orderDesc(DiaryEntityDao.Properties.Date)
                .build();
        ItemEntity entity = entityItemDao.load(diaryId);
        entity.setItemCount(query.list().size());
        entityItemDao.update(entity);
        RxBus.getInstance()
                .post(new MainRefreshEvent());
    }

    @Override
    public void save(Long diaryId, DiaryEntity entity) {
        mEntityDao.save(entity);
        editItemEntityCount(diaryId);
        mView.saveDone();
    }
}
