package sunxl8.your_diary.util;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import sunxl8.your_diary.base.BaseApplication;
import sunxl8.your_diary.ui.splash.SplashActivity;

/**
 * Created by sunxl8 on 2017/2/16.
 */

public class MyActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    private Callback mCallback;

    private boolean isBackground = false;

    private int count = 0;

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (count == 0 && isBackground) {
            if (mCallback != null) {
                mCallback.backToForeground();
            }
        }
        if (!(activity instanceof SplashActivity)) {
            count++;
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
        if (count == 1) {
            isBackground = true;
            if (mCallback != null) {
                mCallback.foreToBackground();
            }
        }
        if (!(activity instanceof SplashActivity)) {
            count--;
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }

    public interface Callback {
        void backToForeground();

        void foreToBackground();
    }
}
