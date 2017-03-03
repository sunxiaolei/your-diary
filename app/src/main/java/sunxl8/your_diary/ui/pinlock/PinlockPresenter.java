package sunxl8.your_diary.ui.pinlock;

import android.os.Build;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;

import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.base.BasePresenter;
import sunxl8.your_diary.constant.Constant;
import sunxl8.your_diary.util.SPUtils;

/**
 * Created by sunxl8 on 2017/2/17.
 */

public class PinlockPresenter extends BasePresenter<PinlockContract.View> implements PinlockContract.Presenter {

    private SPUtils sp;
    private FingerprintManagerCompat mFingerprintManagerCompat;

    protected PinlockPresenter(BaseActivity activity) {
        super(activity);
        sp = new SPUtils(activity, Constant.SP_PIN);
        mFingerprintManagerCompat = FingerprintManagerCompat.from(activity);
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

    @Override
    public void verifyFingerprint() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mFingerprintManagerCompat.authenticate(null, 0, null, new FingerprintManagerCompat.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errMsgId, CharSequence errString) {
                    super.onAuthenticationError(errMsgId, errString);
                    mView.verifyFail();
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    mView.verifyFail();
                }

                @Override
                public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    mView.verifySuccess();
                }

                @Override
                public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                    super.onAuthenticationHelp(helpMsgId, helpString);
                }
            }, null);
        }
    }
}
