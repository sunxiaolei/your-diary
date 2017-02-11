package sunxl8.your_diary.ui.main;

import org.greenrobot.greendao.query.Query;

import java.util.Date;
import java.util.List;

import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.base.BaseApplication;
import sunxl8.your_diary.base.BasePresenter;
import sunxl8.your_diary.base.IView;
import sunxl8.your_diary.constant.Constant;
import sunxl8.your_diary.db.dao.DaoSession;
import sunxl8.your_diary.db.dao.ItemEntityDao;
import sunxl8.your_diary.db.entity.ItemEntity;

import static android.R.id.list;

/**
 * Created by sunxl8 on 2017/2/10.
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private ItemEntityDao entityDao;

    public MainPresenter(BaseActivity activity) {
        super(activity);
        DaoSession daoSession = ((BaseApplication) activity.getApplication()).getDaoSession();
        entityDao = daoSession.getItemEntityDao();
    }

    @Override
    public void getItemList(String account) {
        Query query = entityDao.queryBuilder()
                .where(ItemEntityDao.Properties.Account.eq(account))
                .build();
        List<ItemEntity> list = query.list();
        mView.showItemList(list);
    }

    @Override
    public void addItem(int type, String name) {
        ItemEntity entityContact = new ItemEntity();
        entityContact.setAccount(BaseApplication.account);
        entityContact.setDate(new Date());
        entityContact.setItemTitle(name);
        entityContact.setItemType(type);
        entityDao.insert(entityContact);
        getItemList(BaseApplication.account);
    }
}
