package sunxl8.your_diary.ui.setting;

import android.widget.Switch;

import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.android.ActivityEvent;

import butterknife.BindView;
import rx.functions.Action1;
import sunxl8.your_diary.R;
import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.base.IPresenter;
import sunxl8.your_diary.constant.Constant;
import sunxl8.your_diary.event.PinlockCancelEvent;
import sunxl8.your_diary.ui.pinlock.PinlockActivity;
import sunxl8.your_diary.util.RxBus;
import sunxl8.your_diary.util.SPUtils;

/**
 * Created by sunxl8 on 2017/2/17.
 */

public class SettingActivity extends BaseActivity {

    @BindView(R.id.switch_setting_switch_pin)
    Switch switchPin;

    private boolean isPinOpen;

    @Override
    protected IPresenter createPresenter() {
        return null;
    }

    @Override
    protected int setContentViewId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        SPUtils spPin = new SPUtils(this, Constant.SP_PIN);
        isPinOpen = spPin.getBoolean(Constant.SP_PIN_STATUS, false);
        switchPin.setChecked(isPinOpen);
        switchPin.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (spPin.getString(Constant.SP_PIN_PASSWORD) == null) {
                    PinlockActivity.startPinlockActivity(SettingActivity.this, PinlockActivity.ACTION_SET);
                }
            }
            switchPin.setChecked(isChecked);
            spPin.putBoolean(Constant.SP_PIN_STATUS, isChecked);
        });

        RxBus.getInstance().onEvent(PinlockCancelEvent.class)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(pinlockCancelEvent -> {
                    switchPin.setChecked(false);
                });
    }

    @Override
    protected void initData() {

    }
}
