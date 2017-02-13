package sunxl8.your_diary.ui.diary.list;

import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.base.BasePresenter;

/**
 * Created by sunxl8 on 2017/2/13.
 */

public class DiaryListPresenter extends BasePresenter<DiaryListContract.View> implements DiaryListContract.Presenter {

    protected DiaryListPresenter(BaseActivity activity) {
        super(activity);
    }
}
