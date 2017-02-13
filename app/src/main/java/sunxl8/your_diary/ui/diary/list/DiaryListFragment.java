package sunxl8.your_diary.ui.diary.list;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.Mac;

import butterknife.BindView;
import sunxl8.your_diary.R;
import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.base.BaseFragment;
import sunxl8.your_diary.db.entity.DiaryEntity;

/**
 * Created by sunxl8 on 2017/2/13.
 */

public class DiaryListFragment extends BaseFragment<DiaryListPresenter> implements DiaryListContract.View {

    @BindView(R.id.rv_diary_list)
    RecyclerView rvList;

    private DiaryListAdapter mAdapter;

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
    }

    @Override
    protected void initData() {
        List<DiaryEntity> list = new ArrayList<>();
        list.add(new DiaryEntity());
        list.add(new DiaryEntity());
        list.add(new DiaryEntity());
        list.add(new DiaryEntity());
        mAdapter = new DiaryListAdapter(getActivity(), list);
        rvList.setAdapter(mAdapter);
    }
}
