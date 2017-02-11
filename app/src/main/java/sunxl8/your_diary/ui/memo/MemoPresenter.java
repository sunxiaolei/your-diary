package sunxl8.your_diary.ui.memo;

import org.greenrobot.greendao.query.Query;

import java.util.List;

import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.base.BaseApplication;
import sunxl8.your_diary.base.BasePresenter;
import sunxl8.your_diary.db.dao.DaoSession;
import sunxl8.your_diary.db.dao.MemoEntityDao;
import sunxl8.your_diary.db.entity.ItemEntity;
import sunxl8.your_diary.db.entity.MemoEntity;

/**
 * Description: <br>
 * author: sun<br>
 * date: 2017/2/11.<br>
 * Email：xiaoleisun92@gmail.com
 */

public class MemoPresenter extends BasePresenter<MemoContract.View> implements MemoContract.Presenter {

    private MemoEntityDao entityDao;

    protected MemoPresenter(BaseActivity activity) {
        super(activity);
        DaoSession daoSession = ((BaseApplication) activity.getApplication()).getDaoSession();
        entityDao = daoSession.getMemoEntityDao();
    }

    @Override
    public void addItem(Long memoId, String string) {
        MemoEntity entity = new MemoEntity();
        entity.setMemoId(memoId);
        entity.setMemo(string);
        entityDao.insert(entity);
        getItemList(memoId);
    }

    @Override
    public void getItemList(Long memoId) {
        Query query = entityDao.queryBuilder()
                .where(MemoEntityDao.Properties.MemoId.eq(memoId))
                .orderDesc(MemoEntityDao.Properties.Id)
                .build();
        List<MemoEntity> list = query.list();
        mView.showList(list);
    }
}
