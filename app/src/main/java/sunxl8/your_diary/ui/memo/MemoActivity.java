package sunxl8.your_diary.ui.memo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.android.ActivityEvent;

import java.util.List;

import butterknife.BindView;
import rx.functions.Action1;
import sunxl8.your_diary.R;
import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.db.entity.MemoEntity;

/**
 * Description: <br>
 * author: sun<br>
 * date: 2017/2/11.<br>
 * Email：xiaoleisun92@gmail.com
 */

public class MemoActivity extends BaseActivity<MemoPresenter> implements MemoContract.View {

    @BindView(R.id.tv_memo_title)
    TextView tvTitle;
    @BindView(R.id.iv_memo_edit)
    ImageView ivEdit;
    @BindView(R.id.tv_memo_save)
    TextView tvSave;
    @BindView(R.id.et_memo_new)
    EditText etNew;
    @BindView(R.id.rv_memo)
    RecyclerView rvMemo;

    private MemoAdapter mAdapter;

    private Long memoId;

    @Override
    protected MemoPresenter createPresenter() {
        return new MemoPresenter(this);
    }

    @Override
    protected int setContentViewId() {
        return R.layout.activity_memo;
    }

    @Override
    protected void initView() {
        RxView.clicks(tvSave)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(aVoid -> {
                    if (TextUtils.isEmpty(etNew.getText().toString())) {
                        showToast("请输入有效字符");
                        return;
                    }
                    mPresenter.addItem(memoId, etNew.getText().toString());
                    etNew.setText("");
                });
        rvMemo.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        tvTitle.setText(getIntent().getStringExtra("title"));
        memoId = getIntent().getLongExtra("id", 0);
        mPresenter.getItemList(memoId);
    }

    public static void startMemoActivity(Context context, Long id, String title) {
        Intent intent = new Intent(context, MemoActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    public void showList(List<MemoEntity> list) {
        if (list != null && list.size() > 0) {
            mAdapter = new MemoAdapter(this, list);
            rvMemo.setAdapter(mAdapter);
        }
    }
}
