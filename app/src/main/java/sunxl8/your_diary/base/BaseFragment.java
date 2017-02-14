package sunxl8.your_diary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by sunxl8 on 2016/12/21.
 */

public abstract class BaseFragment<T extends IPresenter> extends RxFragment implements IView {

    private T mPresenter;
    private Unbinder mUnbinder;
    protected BaseActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(setContentViewId(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        if (mPresenter == null) {
            mPresenter = createPresenter();
            mPresenter.attachView(this);
        }
        mActivity = (BaseActivity) getActivity();
        initView();
        initData();
        return view;
    }

    protected abstract T createPresenter();

    protected abstract int setContentViewId();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) mPresenter.detachView();
        if (mUnbinder != null) mUnbinder.unbind();
    }
}
