package sunxl8.your_diary.ui.pinlock;

import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.base.BasePresenter;
import sunxl8.your_diary.constant.Constant;
import sunxl8.your_diary.util.SPUtils;

/**
 * Created by sunxl8 on 2017/2/17.
 */

public class PinlockPresenter extends BasePresenter<PinlockContract.View> implements PinlockContract.Presenter {

    private SPUtils sp;

    protected PinlockPresenter(BaseActivity activity) {
        super(activity);
        sp = new SPUtils(activity, Constant.SP_PIN);
    }

    @Override
    public void verifyPin(String pin) {
        String pwd = sp.getString(Constant.SP_PIN_PASSWORD, "");
        if (pwd.equals(pin)) {
            mView.verifySuccess();
        } else {
            mView.verifyFail();
        }
    }

    @Override
    public void savePin(String pin) {
        sp.putString(Constant.SP_PIN_PASSWORD, pin);
    }
}
