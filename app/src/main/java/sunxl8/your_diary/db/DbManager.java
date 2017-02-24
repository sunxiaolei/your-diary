package sunxl8.your_diary.db;

import android.content.Context;

import org.greenrobot.greendao.query.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import sunxl8.your_diary.base.BaseApplication;
import sunxl8.your_diary.db.dao.DaoSession;
import sunxl8.your_diary.db.dao.DiaryEntityDao;
import sunxl8.your_diary.db.dao.ItemEntityDao;
import sunxl8.your_diary.db.entity.DiaryEntity;
import sunxl8.your_diary.db.entity.ItemEntity;
import sunxl8.your_diary.event.MainRefreshEvent;
import sunxl8.your_diary.util.RxBus;
import sunxl8.your_diary.util.TimeUtils;

/**
 * Created by sunxl8 on 2017/2/21.
 */

public class DbManager {

    private static DbManager manager;
    private static DaoSession daoSession;

    private static DiaryEntityDao mDiaryEntityDao;
    private static ItemEntityDao mItemEntityDao;

    private DbManager(Context context) {
        if (daoSession == null) {
            daoSession = ((BaseApplication) context).getDaoSession();
            mDiaryEntityDao = daoSession.getDiaryEntityDao();
            mItemEntityDao = daoSession.getItemEntityDao();
        }
    }

    public static void init(Context context) {
        if (manager == null) {
            manager = new DbManager(context);
        }
    }

    public static void addDiary(DiaryEntity entity) {
        mDiaryEntityDao.save(entity);
    }

    /**
     * 获取符合id条件所有diary
     *
     * @param id
     * @return
     */
    public static List<DiaryEntity> queryDiaryListByItemId(Long id) {
        return queryDiaryListByItemIdPage(id, 0);
    }

    /**
     * 获取符合id条件所有diary
     *
     * @param id
     * @return
     */
    public static List<DiaryEntity> queryDiaryListByItemIdPage(Long id, int page) {
        Query query = mDiaryEntityDao.queryBuilder()
                .where(DiaryEntityDao.Properties.DiaryId.eq(id))
                .orderDesc(DiaryEntityDao.Properties.Date)
                .offset(page * 10)
                .limit(10)
                .build();
        List<DiaryEntity> list = updateDiaryListByDate(query.list());
        return list;
    }

    /**
     * 获取符合id calendar条件所有diary
     *
     * @param id
     * @param calendar
     * @return
     */
    public static List<DiaryEntity> queryDiaryListByDate(Long id, Calendar calendar) {
        Query query = mDiaryEntityDao.queryBuilder()
                .where(DiaryEntityDao.Properties.DiaryId.eq(id))
                .orderDesc(DiaryEntityDao.Properties.Date)
                .build();
        List<DiaryEntity> list = getDiaryListByDate(query.list(), calendar);
        return list;
    }

    private static List<DiaryEntity> getDiaryListByDate(List<DiaryEntity> list, Calendar calendar) {
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

    private static List<DiaryEntity> updateDiaryListByDate(List<DiaryEntity> list) {
        List<String> dateList = new ArrayList<>();
        List<DiaryEntity> listNew = new ArrayList<>();
        for (DiaryEntity entity : list) {
            String date = TimeUtils.date2String(entity.getDate(), new SimpleDateFormat("yyyy-MM-dd"));
            if (!dateList.contains(date)) {
                dateList.add(date);
                entity.setShowDate(true);
                mDiaryEntityDao.update(entity);
            } else {
                entity.setShowDate(false);
                mDiaryEntityDao.update(entity);
            }
            listNew.add(entity);
        }
        return listNew;
    }

    /**
     * 根据id删除diary
     *
     * @param id
     */
    public static void deleteDiaryById(Long id) {
        mDiaryEntityDao.deleteByKey(id);
    }

    /**
     * 更新item count数值
     *
     * @param id
     */
    public static void updateItemCountByItemId(Long id) {
        Query query = mDiaryEntityDao.queryBuilder()
                .where(DiaryEntityDao.Properties.DiaryId.eq(id))
                .orderDesc(DiaryEntityDao.Properties.Date)
                .build();
        ItemEntity entity = mItemEntityDao.load(id);
        entity.setItemCount(query.list().size());
        mItemEntityDao.update(entity);
        RxBus.getInstance()
                .post(new MainRefreshEvent());
    }
}
