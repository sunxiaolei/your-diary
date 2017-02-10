package sunxl8.your_diary.base;


/**
 * Created by sunxl8 on 2016/12/21.
 */

public abstract class BasePresenter<T extends IView> implements IPresenter<T> {

    protected T mView;

    protected BaseActivity mActivity;

    protected BasePresenter(BaseActivity activity) {
        mActivity = activity;
    }

    /**
     * Presenter持有View的引用
     */
    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    /**
     * Presenter和View解绑
     */
    @Override
    public void detachView() {
        this.mView = null;
    }

}
