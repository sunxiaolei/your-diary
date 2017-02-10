package sunxl8.your_diary.ui.login;

import sunxl8.your_diary.R;
import sunxl8.your_diary.base.BaseActivity;

/**
 * Created by sunxl8 on 2017/2/10.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int setContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
