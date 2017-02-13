package sunxl8.your_diary.ui.diary.calendar;

import android.os.Bundle;
import android.widget.FrameLayout;

import butterknife.BindView;
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
    }

    @Override
    protected void initData() {
    }
}
