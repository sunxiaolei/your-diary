package sunxl8.your_diary.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by sunxl8 on 2016/12/21.
 */

public abstract class BaseActivity<T extends IPresenter> extends RxAppCompatActivity implements IView {

    protected T mPresenter;
    protected Unbinder mUnbinder;

    private ProgressDialog dialogLoading;
    private AlertDialog dialog;

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        super.onCreate(savedInstanceState);
        setContentView(setContentViewId());
        mUnbinder = ButterKnife.bind(this);
        initView();
        initData();
    }

    protected abstract T createPresenter();

    protected abstract int setContentViewId();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.detachView();
        if (mUnbinder != null) mUnbinder.unbind();
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showLoading() {
        if (dialogLoading == null) {
            dialogLoading = new ProgressDialog(this);
        }
        dialogLoading.show();
    }

    public void showDialog(String msg) {
        if (dialog == null) {
            dialog = new AlertDialog.Builder(this)
                    .setPositiveButton("确定", null)
                    .create();
        }
        dialog.setMessage(msg);
        dialog.show();
    }

    public void showDialog(String msg, DialogInterface.OnClickListener listener) {
        if (dialog == null) {
            dialog = new AlertDialog.Builder(this)
                    .setPositiveButton("确定", listener)
                    .setNegativeButton("取消", null)
                    .create();
        }
        dialog.setMessage(msg);
        dialog.show();
    }

    public void dismissDialog() {
        if (dialogLoading != null) {
            dialogLoading.dismiss();
        }
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public void hideKeyboard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
