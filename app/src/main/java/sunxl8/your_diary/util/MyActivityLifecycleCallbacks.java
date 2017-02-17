package sunxl8.your_diary.util;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Created by sunxl8 on 2017/2/16.
 */

public class MyActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    private Callback mCallback;

    private int count = 0;

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (count == 0) {
            if (mCallback != null) {
                mCallback.backToForeground();
            }
        }
        count++;
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
            if (mCallback != null) {
                mCallback.foreToBackground();
            }
        }
        count--;
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
