package sunxl8.your_diary.ui.diary.calendar;

import org.greenrobot.greendao.query.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import sunxl8.your_diary.ui.diary.list.DiaryListContract;
import sunxl8.your_diary.util.RxBus;
import sunxl8.your_diary.util.TimeUtils;

/**
 * Created by sunxl8 on 2017/2/13.
 */

public class DiaryCalendarPresenter extends BasePresenter<DiaryCalendarContract.View> implements DiaryCalendarContract.Presenter {

    private DiaryEntityDao mEntityDao;
    private ItemEntityDao mItemEntityDao;

    private Calendar mCalendar;

    protected DiaryCalendarPresenter(BaseActivity activity) {
        super(activity);
        DaoSession daoSession = ((BaseApplication) activity.getApplication()).getDaoSession();
        mEntityDao = daoSession.getDiaryEntityDao();
        mItemEntityDao = daoSession.getItemEntityDao();
    }

    @Override
    public void getDiaryList(Long diaryId, Calendar calendar) {
        Query query = mEntityDao.queryBuilder()
                .where(DiaryEntityDao.Properties.DiaryId.eq(diaryId))
                .orderDesc(DiaryEntityDao.Properties.Date)
                .build();
        List<DiaryEntity> list = getList(query.list(), calendar);
        mView.showDiaryList(list);
    }

    @Override
    public void deleteDary(Long id, Long diaryId) {
        mEntityDao.deleteByKey(id);
        editItemEntityCount(diaryId);
        getDiaryList(diaryId, mCalendar);
    }

    private List<DiaryEntity> getList(List<DiaryEntity> list, Calendar calendar) {
        mCalendar = calendar;
        List<DiaryEntity> listRes = new ArrayList<>();
        for (DiaryEntity entity : list) {
            Calendar c = Calendar.getInstance();
            c.setTime(entity.getDate());
            if (c.get(Calendar.DATE) == calendar.get(Calendar.DATE)) {
                listRes.add(entity);
            }
        }
        return listRes;
    }

    private void editItemEntityCount(Long diaryId) {
        Query query = mEntityDao.queryBuilder()
                .where(DiaryEntityDao.Properties.DiaryId.eq(diaryId))
                .orderDesc(DiaryEntityDao.Properties.Date)
                .build();
        ItemEntity entity = mItemEntityDao.load(diaryId);
        entity.setItemCount(query.list().size());
        mItemEntityDao.update(entity);
        RxBus.getInstance()
                .post(new MainRefreshEvent());
    }
}
