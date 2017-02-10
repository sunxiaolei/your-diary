package sunxl8.your_diary.ui.main;

import sunxl8.your_diary.R;
import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.widget.MyAlertDialog;

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
        MyAlertDialog dialog = new MyAlertDialog.Builder(getApplicationContext())
                .setTitle("没有内容")
                .setContent("日记本是空的╮(╯▽╰)╭")
                .setAffirm("新建一篇日记")
                .setListener(view -> {
                    showToast("add diary");
                })
                .build();
        dialog.show(getSupportFragmentManager(), "");
    }

    @Override
    protected void initData() {

    }
}
