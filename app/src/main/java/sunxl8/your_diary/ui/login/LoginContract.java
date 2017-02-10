package sunxl8.your_diary.ui.login;

import sunxl8.your_diary.base.IPresenter;
import sunxl8.your_diary.base.IView;

/**
 * Created by sunxl8 on 2017/2/10.
 */

public interface LoginContract {

    interface View extends IView {

    }

    interface Presenter extends IPresenter<View> {

        void login(String account, String password);

        void addAccount(String account, String password);

    }
}
