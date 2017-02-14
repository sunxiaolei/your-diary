package sunxl8.your_diary.ui.diary.list;

import org.greenrobot.greendao.query.Query;

import java.util.List;

import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.base.BaseApplication;
import sunxl8.your_diary.base.BasePresenter;
import sunxl8.your_diary.db.dao.DaoSession;
import sunxl8.your_diary.db.dao.DiaryEntityDao;
import sunxl8.your_diary.db.entity.DiaryEntity;

/**
 * Created by sunxl8 on 2017/2/13.
 */

public class DiaryListPresenter extends BasePresenter<DiaryListContract.View> implements DiaryListContract.Presenter {

    private DiaryEntityDao mEntityDao;

    protected DiaryListPresenter(BaseActivity activity) {
        super(activity);
        DaoSession daoSession = ((BaseApplication) activity.getApplication()).getDaoSession();
        mEntityDao = daoSession.getDiaryEntityDao();
    }

    @Override
    public void getDiaryList() {
        Query query = mEntityDao.queryBuilder()
                .orderDesc(DiaryEntityDao.Properties.Date)
                .build();
        List<DiaryEntity> list = query.list();
        mView.showDiaryList(list);
    }
}
