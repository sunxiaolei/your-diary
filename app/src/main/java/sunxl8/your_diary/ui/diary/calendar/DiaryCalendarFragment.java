package sunxl8.your_diary.ui.diary.calendar;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.android.FragmentEvent;

import butterknife.BindView;
import rx.functions.Action1;
import sunxl8.your_diary.R;
import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.base.BaseFragment;
import sunxl8.your_diary.widget.DiaryCalendar;
import sunxl8.your_diary.widget.DiaryCalendarView;

/**
 * Created by sunxl8 on 2017/2/13.
 */

public class DiaryCalendarFragment extends BaseFragment<DiaryCalendarPresenter> implements DiaryCalendarContract.View {

    @BindView(R.id.layout_diary_calendar)
    FrameLayout mLayout;
    @BindView(R.id.iv_diary_calendar_arrow)
    ImageView ivArrow;
    @BindView(R.id.rv_diary_calendar)
    RecyclerView rvList;

    private boolean showList = false;

    public static DiaryCalendarFragment newInstance() {
        DiaryCalendarFragment fragment = new DiaryCalendarFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected DiaryCalendarPresenter createPresenter() {
        return new DiaryCalendarPresenter((BaseActivity) getActivity());
    }

    @Override
    protected int setContentViewId() {
        return R.layout.fragment_diary_calendar;
    }

    @Override
    protected void initView() {
        DiaryCalendarView calendarView = new DiaryCalendarView(getActivity());
        mLayout.addView(calendarView);

        RxView.clicks(ivArrow)
                .compose(this.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(aVoid -> {
                    if (showList) {
                        mLayout.setVisibility(View.VISIBLE);
                        rvList.setVisibility(View.GONE);
                    } else {
                        mLayout.setVisibility(View.GONE);
                        rvList.setVisibility(View.VISIBLE);
                    }
                    showList = !showList;
                });


    }

    @Override
    protected void initData() {
    }
}
