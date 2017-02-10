package sunxl8.your_diary.ui.login;

import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.base.BasePresenter;

/**
 * Created by sunxl8 on 2017/2/10.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    protected LoginPresenter(BaseActivity activity) {
        super(activity);
    }

    @Override
    public void login(String account, String password) {

    }

    @Override
    public void addAccount(String account, String password) {

    }
}
