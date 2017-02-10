package sunxl8.your_diary.ui.main;

import sunxl8.your_diary.base.IPresenter;
import sunxl8.your_diary.base.IView;

/**
 * Created by sunxl8 on 2017/2/10.
 */

public interface MainContract {

    interface View extends IView {

    }

    interface Presenter extends IPresenter<View> {
        void getLocalUser();
    }
}
