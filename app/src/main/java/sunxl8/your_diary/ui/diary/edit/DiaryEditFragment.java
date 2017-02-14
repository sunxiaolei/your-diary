package sunxl8.your_diary.ui.diary.edit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jakewharton.rxbinding.support.v4.view.RxViewPager;
import com.jakewharton.rxbinding.view.RxView;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.LubanOptions;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.model.TakePhotoOptions;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.trello.rxlifecycle.android.FragmentEvent;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import me.shaohui.advancedluban.Luban;
import rx.functions.Action1;
import sunxl8.your_diary.R;
import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.base.BaseFragment;
import sunxl8.your_diary.db.entity.DiaryEntity;
import sunxl8.your_diary.event.DiaryEditDoneEvent;
import sunxl8.your_diary.ui.diary.DiaryActivity;
import sunxl8.your_diary.ui.diary.calendar.DiaryCalendarContract;
import sunxl8.your_diary.ui.diary.calendar.DiaryCalendarPresenter;
import sunxl8.your_diary.util.RxBus;
import sunxl8.your_diary.util.TimeUtils;
import sunxl8.your_diary.widget.DiaryCalendarView;
import sunxl8.your_diary.widget.RichEditTextView;

/**
 * Created by sunxl8 on 2017/2/13.
 */

public class DiaryEditFragment extends BaseFragment<DiaryEditPresenter> implements DiaryEditContract.View, TakePhoto.TakeResultListener, InvokeListener {

    @BindView(R.id.et_diary_edit_title)
    EditText etTitle;
    @BindView(R.id.tv_diary_edit_date)
    TextView tvDate;
    @BindView(R.id.tv_diary_edit_location)
    TextView tvLocation;
    @BindView(R.id.spinner_diary_edit_mood)
    Spinner spinnerMood;
    @BindView(R.id.spinner_diary_edit_weather)
    Spinner spinnerWeather;
    @BindView(R.id.et_diary_edit_content)
    RichEditTextView etContent;
    @BindView(R.id.layout_diary_edit_location)
    RelativeLayout layoutLocation;
    @BindView(R.id.layout_diary_edit_picture)
    RelativeLayout layoutPicture;
    @BindView(R.id.layout_diary_edit_clean)
    RelativeLayout layoutClean;
    @BindView(R.id.layout_diary_edit_save)
    RelativeLayout layoutSave;


    public static DiaryEditFragment newInstance() {
        DiaryEditFragment fragment = new DiaryEditFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected DiaryEditPresenter createPresenter() {
        return new DiaryEditPresenter(mActivity);
    }

    @Override
    protected int setContentViewId() {
        return R.layout.fragment_diary_edit;
    }

    @Override
    protected void initView() {
        tvDate.setText(TimeUtils.getCurTimeString(new SimpleDateFormat("yyyy-MM-dd HH:mm")));
        tvLocation.setText("");

        String[] test = {"晴", "阴", "雨", "雪"};
        spinnerMood.setAdapter(new ArrayAdapter<String>(mActivity, android.R.layout.simple_list_item_1, test));
        spinnerWeather.setAdapter(new ArrayAdapter<String>(mActivity, android.R.layout.simple_list_item_1, test));

        RxView.clicks(layoutLocation)
                .compose(this.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(aVoid -> {
                    mActivity.showToast("Location");
                });
        RxView.clicks(layoutPicture)
                .compose(this.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(aVoid -> {
                    getTakePhoto().onPickFromGallery();
                });
        RxView.clicks(layoutClean)
                .compose(this.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(aVoid -> {

                });
        RxView.clicks(layoutSave)
                .compose(this.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(aVoid -> {
                    save();
                });
    }

    @Override
    protected void initData() {
    }

    private TakePhoto takePhoto;
    private TakePhotoOptions options;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(getActivity(), type, invokeParam, this);
    }

    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));

//            CompressConfig config = new CompressConfig.Builder()
//                    .setMaxSize(800*600)
//                    .setMaxPixel(800)
//                    .enableReserveRaw(true)
//                    .create();
//            takePhoto.onEnableCompress(config, true);
            options = new TakePhotoOptions.Builder()
                    .setWithOwnGallery(true)
                    .create();
        }
        takePhoto.setTakePhotoOptions(options);
        return takePhoto;
    }

    @Override
    public void takeSuccess(TResult result) {
        ArrayList<TImage> images = result.getImages();
        Glide.with(getActivity()).load(images.get(0).getOriginalPath()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                etContent.addImage(resource, images.get(0).getOriginalPath());
            }
        });
    }

    @Override
    public void takeFail(TResult result, String msg) {
        System.out.println();
    }

    @Override
    public void takeCancel() {
        System.out.println();
    }

    private InvokeParam invokeParam;

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    private void save() {
        if (TextUtils.isEmpty(etTitle.getText().toString())) {
            mActivity.showToast("标题不能为空");
            return;
        }
        if (TextUtils.isEmpty(etContent.getRichText())) {
            mActivity.showToast("内容不能为空");
            return;
        }
        DiaryEntity entity = new DiaryEntity();
        entity.setTitle(etTitle.getText().toString());
        entity.setDate(new Date());
        entity.setContent(etContent.getRichText());
        mPresenter.save(entity);
    }

    @Override
    public void saveDone() {
        RxBus.getInstance().post(new DiaryEditDoneEvent());
    }
}
