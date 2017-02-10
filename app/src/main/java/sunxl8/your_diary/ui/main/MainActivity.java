package sunxl8.your_diary.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import sunxl8.your_diary.R;
import sunxl8.your_diary.base.BaseActivity;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int setContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
