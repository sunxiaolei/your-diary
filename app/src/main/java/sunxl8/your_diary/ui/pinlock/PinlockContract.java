package sunxl8.your_diary.ui.pinlock;

import sunxl8.your_diary.base.IPresenter;
import sunxl8.your_diary.base.IView;

/**
 * Created by sunxl8 on 2017/2/17.
 */

public interface PinlockContract {

    interface View extends IView {
        void verifySuccess();
        void verifyFail();
    }

    interface Presenter extends IPresenter<View> {
        void verifyPin(String pin);
        void savePin(String pin);
    }
}
