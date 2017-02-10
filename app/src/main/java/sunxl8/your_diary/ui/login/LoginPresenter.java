package sunxl8.your_diary.ui.login;

import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.base.BasePresenter;
import sunxl8.your_diary.constant.Constant;
import sunxl8.your_diary.util.EncryptUtils;
import sunxl8.your_diary.util.SPUtils;

/**
 * Created by sunxl8 on 2017/2/10.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    private SPUtils sp;

    protected LoginPresenter(BaseActivity activity) {
        super(activity);
        sp = new SPUtils(activity, Constant.SP_ACCOUNT);
    }

    @Override
    public void login(String account, String password) {
        if (sp.getString(Constant.SP_ACCOUNT_NUMBER) != null
                && sp.getString(Constant.SP_ACCOUNT_PASSWORD) != null
                && sp.getString(Constant.SP_ACCOUNT_NUMBER).equals(account)
                && sp.getString(Constant.SP_ACCOUNT_PASSWORD).equals(password)) {
            mView.gotoMain();
        } else {
            mView.loginFailed();
        }
    }

    @Override
    public void addAccount(String account, String password) {
        String encryptPwd = EncryptUtils.encryptMD5ToString(password);
        sp.putString(Constant.SP_ACCOUNT_NUMBER, account);
        sp.putString(Constant.SP_ACCOUNT_PASSWORD, encryptPwd);
        login(account, encryptPwd);
    }
}
