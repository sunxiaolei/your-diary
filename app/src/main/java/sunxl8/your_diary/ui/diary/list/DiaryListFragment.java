package sunxl8.your_diary.ui.diary.list;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.android.FragmentEvent;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.Mac;

import butterknife.BindView;
import sunxl8.your_diary.R;
import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.base.BaseFragment;
import sunxl8.your_diary.db.entity.DiaryEntity;
import sunxl8.your_diary.event.DiaryEditDoneEvent;
import sunxl8.your_diary.util.RxBus;

/**
 * Created by sunxl8 on 2017/2/13.
 */

public class DiaryListFragment extends BaseFragment<DiaryListPresenter> implements DiaryListContract.View {

    @BindView(R.id.rv_diary_list)
    RecyclerView rvList;

    private DiaryListAdapter mAdapter;
    private List<DiaryEntity> mList;

    public static DiaryListFragment newInstance() {
        DiaryListFragment fragment = new DiaryListFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected DiaryListPresenter createPresenter() {
        return new DiaryListPresenter((BaseActivity) getActivity());
    }

    @Override
    protected int setContentViewId() {
        return R.layout.fragment_diary_list;
    }

    @Override
    protected void initView() {
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        RxBus.getInstance().onEvent(DiaryEditDoneEvent.class)
                .compose(this.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(event -> {
                    initData();
                });
    }

    @Override
    protected void initData() {
        mPresenter.getDiaryList();
    }

    @Override
    public void showDiaryList(List<DiaryEntity> list) {
        if (list != null && list.size() > 0) {
            mList = list;
            mAdapter = new DiaryListAdapter(getActivity(), mList);
            rvList.setAdapter(mAdapter);
        }
    }

}
