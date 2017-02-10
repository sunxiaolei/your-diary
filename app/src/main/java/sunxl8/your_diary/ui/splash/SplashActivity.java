package sunxl8.your_diary.ui.splash;

import android.content.Intent;
import android.widget.TextView;

import com.trello.rxlifecycle.android.ActivityEvent;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;
import sunxl8.your_diary.R;
import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.base.IPresenter;
import sunxl8.your_diary.ui.main.MainActivity;
import sunxl8.your_diary.util.TimeUtils;

/**
 * Created by sunxl8 on 2017/2/10.
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.tv_splash_date)
    TextView tvData;

    @Override
    protected IPresenter createPresenter() {
        return null;
    }

    @Override
    protected int setContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 EEEE");
        tvData.setText(TimeUtils.getCurTimeString(sdf));
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(aLong -> {
                    this.finish();
                    startActivity(new Intent(this, MainActivity.class));
                });
    }

    @Override
    protected void initData() {

    }
}
