package sunxl8.your_diary.ui.main;

import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.base.BasePresenter;

/**
 * Created by sunxl8 on 2017/2/10.
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    public MainPresenter(BaseActivity activity) {
        super(activity);
    }

    @Override
    public void getLocalUser() {

    }
}
