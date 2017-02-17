package sunxl8.your_diary.base;

import android.app.Application;
import android.content.Intent;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import sunxl8.your_diary.constant.Constant;
import sunxl8.your_diary.db.GreenDaoOpenHelper;
import sunxl8.your_diary.db.dao.DaoMaster;
import sunxl8.your_diary.db.dao.DaoSession;
import sunxl8.your_diary.ui.main.MainActivity;
import sunxl8.your_diary.ui.pinlock.PinlockActivity;
import sunxl8.your_diary.util.MyActivityLifecycleCallbacks;
import sunxl8.your_diary.util.SPUtils;

/**
 * Created by sunxl8 on 2016/12/21.
 */

public class BaseApplication extends Application {

    public static String account;

    public static final boolean ENCRYPTED = true;

    public DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init("your-diary")
                .methodCount(3)
                .hideThreadInfo()
                .logLevel(LogLevel.FULL)
                .methodOffset(2);
        initActivityLifecycle();
        initDataBase();
    }

    private void initActivityLifecycle() {
        SPUtils spPin = new SPUtils(this, Constant.SP_PIN);
        MyActivityLifecycleCallbacks callbacks = new MyActivityLifecycleCallbacks();
        this.registerActivityLifecycleCallbacks(callbacks);
        callbacks.setCallback(new MyActivityLifecycleCallbacks.Callback() {
            @Override
            public void backToForeground() {
                boolean isPinOpen = spPin.getBoolean(Constant.SP_PIN_STATUS, false);
                if (isPinOpen) {
                    PinlockActivity.startPinlockActivity(getApplicationContext(), PinlockActivity.ACTION_VERIFY);
                }
            }

            @Override
            public void foreToBackground() {

            }
        });
    }

    private void initDataBase() {
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "yd-db-encrypted" : "yd-db");
//        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("yd-pwd") : helper.getWritableDb();
//        Database db = helper.getWritableDb();
//        daoSession = new DaoMaster(db).newSession();
        GreenDaoOpenHelper helper = new GreenDaoOpenHelper(this, "yd-db", null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
