package com.example.helloworld.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.helloworld.R;

/**
 * Created by hanchenghai on 2018/2/23.
 */

public class CustomDialog extends Dialog implements View.OnClickListener {

    private TextView mTitle, mContent;
    private Button mBtnCancle, mBtnConfirm;


    private String title, content, cancle, confirm;

    private COnCancleListener cancelListener;
    private COnConfirmListener confirmListener;

    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    public CustomDialog(@NonNull Context context, int themeId) {
        super(context, themeId);
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCancle(String cancle, COnCancleListener listener) {
        this.cancle = cancle;
        this.cancelListener = listener;
    }

    public void setConfirm(String confirm, COnConfirmListener listener) {
        this.confirm = confirm;
        this.confirmListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_custom_dialog);
        //获取屏幕宽度
        WindowManager manager = getWindow().getWindowManager();
        Display d = manager.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        Point size = new Point();
        d.getSize(size);
        p.width = (int)(size.x * 0.8);
        getWindow().setAttributes(p);



        mTitle = findViewById(R.id.tv_title);
        mContent = findViewById(R.id.tv_content);
        mBtnConfirm = findViewById(R.id.btn_confirm);
        mBtnCancle = findViewById(R.id.btn_cancel);

        if (!TextUtils.isEmpty(title)) {
            mTitle.setText(title);
        }

        if (!TextUtils.isEmpty(content)) {
            mContent.setText(content);
        }

        if (!TextUtils.isEmpty(confirm)) {
            mBtnConfirm.setText(confirm);
        }

        if (!TextUtils.isEmpty(cancle)) {
            mBtnCancle.setText(cancle);
        }
        mBtnCancle.setOnClickListener(this);
        mBtnConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                if (cancelListener != null) {
                    cancelListener.onCancel(this);
                }
                dismiss();
                break;
            case R.id.btn_confirm:
                if (confirmListener != null) {
                    confirmListener.onConfirm(this);
                }
                dismiss();
                break;
        }
    }


    public interface COnCancleListener {
        void onCancel(CustomDialog dialog);
    }

    public interface COnConfirmListener {
        void onConfirm(CustomDialog dialog);
    }
}
