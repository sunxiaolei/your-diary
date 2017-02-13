package sunxl8.your_diary.ui.diary.edit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import sunxl8.your_diary.R;
import sunxl8.your_diary.base.BaseActivity;
import sunxl8.your_diary.base.BaseFragment;
import sunxl8.your_diary.ui.diary.calendar.DiaryCalendarContract;
import sunxl8.your_diary.ui.diary.calendar.DiaryCalendarPresenter;
import sunxl8.your_diary.widget.DiaryCalendarView;
import sunxl8.your_diary.widget.RichEditTextView;

/**
 * Created by sunxl8 on 2017/2/13.
 */

public class DiaryEditFragment extends BaseFragment<DiaryEditPresenter> implements DiaryEditContract.View, TakePhoto.TakeResultListener, InvokeListener {

    @BindView(R.id.et_diary_edit_content)
    RichEditTextView etContent;
    @BindView(R.id.btn_test)
    Button btnTest;

    public static DiaryEditFragment newInstance() {
        DiaryEditFragment fragment = new DiaryEditFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected DiaryEditPresenter createPresenter() {
        return new DiaryEditPresenter((BaseActivity) getActivity());
    }

    @Override
    protected int setContentViewId() {
        return R.layout.fragment_diary_edit;
    }

    @Override
    protected void initView() {
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTakePhoto().onPickFromGallery();
            }
        });
    }

    @Override
    protected void initData() {
    }

    private TakePhoto takePhoto;

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
        }
        return takePhoto;
    }

    @Override
    public void takeSuccess(TResult result) {
        ArrayList<TImage> images = result.getImages();
        etContent.setRichText("<img src=" + images.get(0).getOriginalPath() + "/>");
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

}
