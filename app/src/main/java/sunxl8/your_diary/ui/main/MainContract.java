package sunxl8.your_diary.ui.main;

import java.util.List;

import sunxl8.your_diary.base.IPresenter;
import sunxl8.your_diary.base.IView;
import sunxl8.your_diary.db.entity.ItemEntity;

/**
 * Created by sunxl8 on 2017/2/10.
 */

public interface MainContract {

    interface View extends IView {
        void showItemList(List<ItemEntity> list);
    }

    interface Presenter extends IPresenter<View> {
        void getItemList(String account);
        void addItem(int type,String name);
    }
}
