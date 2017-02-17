package sunxl8.your_diary.ui.main;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.support.v4.view.RxViewPager;
import com.jakewharton.rxbinding.view.RxView;
import com.shehabic.droppy.DroppyMenuItem;
import com.shehabic.droppy.DroppyMenuPopup;
import com.trello.rxlifecycle.android.ActivityEvent;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;
import sunxl8.your_diary.R;
import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.base.BaseApplication;
import sunxl8.your_diary.constant.Constant;
import sunxl8.your_diary.db.entity.ItemEntity;
import sunxl8.your_diary.event.MainRefreshEvent;
import sunxl8.your_diary.ui.setting.SettingActivity;
import sunxl8.your_diary.util.RxBus;
import sunxl8.your_diary.util.SPUtils;
import sunxl8.your_diary.widget.MyAlertDialog;
import sunxl8.your_diary.widget.MyEditDialog;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.iv_main_plus)
    ImageView ivPlus;
    @BindView(R.id.et_main_search)
    EditText etSearch;
    @BindView(R.id.iv_main_set)
    ImageView ivSet;
    @BindView(R.id.rv_main)
    RecyclerView rvItem;
    @BindView(R.id.tv_main_name)
    TextView tvName;

    private MainItemAdapter itemAdapter;
    private MyEditDialog dialogEdit;

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
        SPUtils sp = new SPUtils(this, Constant.SP_ACCOUNT);
        String name = sp.getString(Constant.SP_ACCOUNT_NAME, "三叶");
        tvName.setText(name);
        RxView.clicks(ivPlus)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(aVoid -> {
                    showPlusDrop();
                });
        RxView.clicks(ivSet)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(aVoid -> {
                    startActivity(new Intent(this, SettingActivity.class));
                });
        rvItem.setLayoutManager(new LinearLayoutManager(this));
        RxBus.getInstance().onEvent(MainRefreshEvent.class)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(mainActivity -> {
                    itemAdapter.notifyDataSetChanged();
                });
        RxView.clicks(tvName)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(aVoid -> {
                    dialogEdit = new MyEditDialog.Builder()
                            .setTitle("你的名字？")
                            .setListener(view -> {
                                sp.putString(Constant.SP_ACCOUNT_NAME, dialogEdit.getEditTextString());
                                dialogEdit.dismiss();
                                tvName.setText(dialogEdit.getEditTextString());
                            })
                            .build();
                    dialogEdit.show(getSupportFragmentManager(), "");
                });
    }

    private void showPlusDrop() {
        DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(this, ivPlus);
        droppyBuilder.addMenuItem(new DroppyMenuItem("新增联系人", R.drawable.ic_plus_contact));
        droppyBuilder.addMenuItem(new DroppyMenuItem("新建日记", R.drawable.ic_plus_diary));
        droppyBuilder.addMenuItem(new DroppyMenuItem("新增备忘录", R.drawable.ic_plus_alert));
        droppyBuilder.setOnClick((v, id) -> showAddDialog(id));
        DroppyMenuPopup droppyMenu = droppyBuilder.build();
        droppyMenu.show();
    }

    @Override
    protected void initData() {
        mPresenter.getItemList(BaseApplication.account);
    }

    @Override
    public void showItemList(List<ItemEntity> list) {
        if (list != null && list.size() > 0) {
            itemAdapter = new MainItemAdapter(this, list);
            rvItem.setAdapter(itemAdapter);
        } else {
            showEmptyDialog();
        }
    }

    private void showAddDialog(int type) {
        dialogEdit = new MyEditDialog.Builder()
                .setTitle("输入条目名称")
                .setListener(view -> {
                    mPresenter.addItem(type, dialogEdit.getEditTextString());
                    dialogEdit.dismiss();
                })
                .build();
        dialogEdit.show(getSupportFragmentManager(), "");
    }


    private void showEmptyDialog() {
        MyAlertDialog dialog = new MyAlertDialog.Builder()
                .setTitle("没有内容")
                .setContent("日记本是空的╮(╯▽╰)╭")
                .setAffirm("新建一篇日记")
                .setListener(view -> showToast("add diary"))
                .build();
        dialog.show(getSupportFragmentManager(), "");
    }

    private boolean isBack = false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isBack) {
                showToast("再次点击退出");
                isBack = true;
                Observable.timer(2, TimeUnit.SECONDS)
                        .subscribe(aLong -> {
                            isBack = false;
                        });
                return true;
            } else {
                MainActivity.this.finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
