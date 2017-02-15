package sunxl8.your_diary.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.support.v4.view.RxViewPager;
import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.android.ActivityEvent;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;
import sunxl8.your_diary.R;
import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.db.entity.DiaryEntity;
import sunxl8.your_diary.util.TimeUtils;

/**
 * Created by sunxl8 on 2017/2/15.
 */

public class MyDiaryDialog extends DialogFragment {

    @BindView(R.id.tv_diary_dialog_month)
    TextView tvMonth;
    @BindView(R.id.tv_diary_dialog_date)
    TextView tvDate;
    @BindView(R.id.tv_diary_dialog_time)
    TextView tvTime;
    @BindView(R.id.tv_diary_dialog_other)
    TextView tvOther;
    @BindView(R.id.iv_diary_dialog_close)
    ImageView ivClose;
    @BindView(R.id.tv_diary_dialog_title)
    TextView tvTitle;
    @BindView(R.id.tv_diary_dialog_subhead)
    TextView tvSubhead;
    @BindView(R.id.et_diary_dialog_content)
    RichEditTextView etContent;

    private String mMonth;
    private String mDate;
    private String mTime;
    private String mWeek;
    private String mTitle;
    private String mSubhead;
    private String mContent;

    public static MyDiaryDialog newInstance(
            String month, String date, String time, String week, String title, String subhead, String content) {
        MyDiaryDialog dialog = new MyDiaryDialog();
        Bundle bundle = new Bundle();
        bundle.putString("month", month);
        bundle.putString("date", date);
        bundle.putString("time", time);
        bundle.putString("week", week);
        bundle.putString("title", title);
        bundle.putString("subhead", subhead);
        bundle.putString("content", content);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        mMonth = getArguments().getString("month");
        mDate = getArguments().getString("date");
        mTime = getArguments().getString("time");
        mWeek = getArguments().getString("week");
        mTitle = getArguments().getString("title");
        mSubhead = getArguments().getString("subhead");
        mContent = getArguments().getString("content");
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.view_dialog_diary, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        tvMonth.setText(mMonth);
        tvDate.setText(mDate);
        tvTime.setText(mTime + "   " + mWeek);
        tvOther.setText("");
        tvTitle.setText(mTitle);
        tvSubhead.setText(mSubhead);
        etContent.setRichText(mContent);
        RxView.clicks(ivClose)
                .compose(((BaseActivity) getActivity()).bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(aVoid -> {
                    this.dismiss();
                });
    }

}
