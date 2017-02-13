package sunxl8.your_diary.ui.memo;

import org.greenrobot.greendao.query.Query;

import java.util.List;

import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.base.BaseApplication;
import sunxl8.your_diary.base.BasePresenter;
import sunxl8.your_diary.db.dao.DaoSession;
import sunxl8.your_diary.db.dao.ItemEntityDao;
import sunxl8.your_diary.db.dao.MemoEntityDao;
import sunxl8.your_diary.db.entity.ItemEntity;
import sunxl8.your_diary.db.entity.MemoEntity;
import sunxl8.your_diary.event.MainRefreshEvent;
import sunxl8.your_diary.util.RxBus;

/**
 * Description: <br>
 * author: sun<br>
 * date: 2017/2/11.<br>
 * Email：xiaoleisun92@gmail.com
 */

public class MemoPresenter extends BasePresenter<MemoContract.View> implements MemoContract.Presenter {

    private MemoEntityDao entityDao;
    private ItemEntityDao entityItemDao;

    protected MemoPresenter(BaseActivity activity) {
        super(activity);
        DaoSession daoSession = ((BaseApplication) activity.getApplication()).getDaoSession();
        entityDao = daoSession.getMemoEntityDao();
        entityItemDao = daoSession.getItemEntityDao();
    }

    @Override
    public void addItem(Long memoId, String string) {
        MemoEntity entity = new MemoEntity();
        entity.setMemoId(memoId);
        entity.setMemo(string);
        entityDao.insert(entity);
        //找到ItemEntity 将count++
        editItemEntityCount(memoId, true);
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

    @Override
    public void deleteItem(Long id, Long memoId) {
        entityDao.deleteByKey(id);
        editItemEntityCount(memoId, false);
    }

    private void editItemEntityCount(Long memoId, boolean plus) {
        ItemEntity entity = entityItemDao.load(memoId);
        int count = entity.getItemCount();
        if (plus) {
            count++;
        } else {
            count--;
        }
        entity.setItemCount(count);
        entityItemDao.update(entity);
        RxBus.getInstance()
                .post(new MainRefreshEvent());
    }
}
