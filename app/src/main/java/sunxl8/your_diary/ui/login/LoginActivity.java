package sunxl8.your_diary.ui.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.android.ActivityEvent;

import butterknife.BindView;
import rx.functions.Action1;
import sunxl8.your_diary.R;
import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.ui.main.MainActivity;

/**
 * Created by sunxl8 on 2017/2/10.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.et_login_account)
    EditText etAccount;
    @BindView(R.id.et_login_password)
    EditText etPassword;
    @BindView(R.id.et_login_password_affirm)
    EditText etPasswordAffirm;
    @BindView(R.id.tv_login_add)
    TextView tvAdd;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    private boolean isLoginPage = true;

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
        RxView.clicks(tvAdd)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(aVoid -> {
                    changeView();
                });
        RxView.clicks(tvLogin)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(aVoid -> {
                    checkData();
                    if (isLoginPage) {
                        mPresenter.login(etAccount.getText().toString(), etPassword.getText().toString());
                    } else {
                        mPresenter.addAccount(etAccount.getText().toString(), etPassword.getText().toString());
                    }
                });
    }

    @Override
    protected void initData() {

    }

    private void changeView() {
        if (isLoginPage) {
            etPasswordAffirm.setVisibility(View.VISIBLE);
            tvAdd.setText("返回登录(⊙ ▽ ⊙)");
            tvLogin.setText("新增账户");
        } else {
            etPasswordAffirm.setVisibility(View.GONE);
            tvAdd.setText("新增一个账户(⊙ ▽ ⊙)");
            tvLogin.setText("登录");
        }
        isLoginPage = !isLoginPage;
    }

    private void checkData() {
        if (TextUtils.isEmpty(etAccount.getText().toString())) {
            showToast("请输入账号");
            return;
        }
        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            showToast("请输入密码");
            return;
        }
        if (!isLoginPage) {
            if (TextUtils.isEmpty(etPasswordAffirm.getText().toString())) {
                showToast("请确认密码");
                return;
            }
            if (!etPassword.getText().toString().equals(etPasswordAffirm.getText().toString())) {
                showToast("两次密码输入不一致");
                return;
            }
        }
    }

}
