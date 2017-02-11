package sunxl8.your_diary.widget;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import sunxl8.your_diary.R;

/**
 * Description: <br>
 * author: sun<br>
 * date: 2017/2/10.<br>
 * Email：xiaoleisun92@gmail.com
 */

public class MyEditDialog extends DialogFragment {

    private static String mTitle = "标题";
    private static String mEtContentHint = "";
    private static String mAffirm = "确定";
    private static View.OnClickListener mListener = null;

    private TextView mTvTitle;
    private EditText mEtContent;
    private TextView mTvAffirm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.view_dialog_edit, container);
        mTvTitle = (TextView) view.findViewById(R.id.tv_dialog_title);
        mEtContent = (EditText) view.findViewById(R.id.et_dialog_content);
        mTvAffirm = (TextView) view.findViewById(R.id.tv_dialog_sure);
        mTvAffirm.setText(mAffirm);
        mTvAffirm.setOnClickListener(mListener);
        mTvTitle.setText(mTitle);
        mEtContent.setHint(mEtContentHint);
        return view;
    }

    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    public void setContentHint(String hint) {
        mEtContent.setHint(hint);
    }

    public String getEditTextString(){
        return mEtContent.getText().toString();
    }

    public void setAffirm(String affirm) {
        mTvAffirm.setText(affirm);
    }

    public void setListener(View.OnClickListener listener) {
        mTvAffirm.setOnClickListener(mListener);
    }

    public static class Builder {

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public Builder setContentHint(String hint) {
            mEtContentHint = hint;
            return this;
        }

        public Builder setAffirm(String affirm) {
            mAffirm = affirm;
            return this;
        }

        public Builder setListener(View.OnClickListener listener) {
            mListener = listener;
            return this;
        }

        public MyEditDialog build() {
            MyEditDialog dialog = new MyEditDialog();
            return dialog;
        }

    }
}
