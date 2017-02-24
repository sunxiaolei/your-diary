package sunxl8.your_diary.ui.diary.list;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.trello.rxlifecycle.android.FragmentEvent;

import java.util.List;

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
    XRecyclerView rvList;
    @BindView(R.id.iv_diary_list_menu)
    RelativeLayout layoutMenu;
    @BindView(R.id.iv_diary_list_pen)
    RelativeLayout layoutPen;
    @BindView(R.id.iv_diary_list_camera)
    RelativeLayout layoutCamera;
    @BindView(R.id.tv_diary_list_count)
    TextView tvCount;

    private DiaryListAdapter mAdapter;

    private Long diaryId;

    private int page = 0;

    public static DiaryListFragment newInstance(Long id) {
        DiaryListFragment fragment = new DiaryListFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("id", id);
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
        rvList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                initData();
            }

            @Override
            public void onLoadMore() {
                page++;
                mPresenter.getDiaryList(diaryId, page);
            }
        });
    }

    @Override
    protected void initData() {
        diaryId = getArguments().getLong("id", 0);
        mPresenter.getDiaryList(diaryId);
    }

    @Override
    public void showDiaryList(List<DiaryEntity> list) {
        rvList.refreshComplete();
        if (list != null) {
            tvCount.setText(list.size() + "  entry");
            mAdapter = new DiaryListAdapter(getActivity(), list, mPresenter);
            rvList.setAdapter(mAdapter);
        }
    }

    @Override
    public void showMoreDiaryList(List<DiaryEntity> list) {
        rvList.loadMoreComplete();
        mAdapter.addData(list);
    }

}
