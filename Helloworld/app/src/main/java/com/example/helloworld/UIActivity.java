package com.example.helloworld;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.helloworld.Fragment.ContainerActivity;
import com.example.helloworld.GridView.GridViewActivity;
import com.example.helloworld.ListView.ListViewActivity;
import com.example.helloworld.RecycleView.RecyclerViewActivity;
import com.example.helloworld.WebView.WebViewActivity;

public class UIActivity extends AppCompatActivity {


    private Button mBtnTextView, mBtnButton, mBtnEditText, mRadioBtn, mcheckBox, mImageView, mListView;


    private Button mBtnGridView;

    private Button mBtnRecyclerView;

    private Button mBtnWebView;

    private Button mBtnToast;

    private Button mBtnDialog;

    private Button mBtnCustomDialog;

    private Button mBtnProgress;

    private Button mBtnPopUpWindow;

    private Button mBtnFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        mBtnTextView = findViewById(R.id.btn_textview);
        mBtnButton = findViewById(R.id.btn_button);
        mBtnEditText = findViewById(R.id.edittext);
        mRadioBtn = findViewById(R.id.btn_radioButton);
        mcheckBox = findViewById(R.id.btn_checkBox);
        mImageView = findViewById(R.id.btn_imageView);
        mListView = findViewById(R.id.btn_listView);
        mBtnGridView = findViewById(R.id.btn_gridView);
        mBtnRecyclerView = findViewById(R.id.btn_recyclerView);
        mBtnWebView = findViewById(R.id.btn_webView);
        mBtnToast = findViewById(R.id.btn_toast);
        mBtnDialog = findViewById(R.id.btn_dialog);
        mBtnProgress = findViewById(R.id.btn_progress);
        mBtnCustomDialog = findViewById(R.id.btn_custom_dialog);
        mBtnPopUpWindow = findViewById(R.id.btn_popUpWindow);
        mBtnFragment = findViewById(R.id.btn_fragment);
        setListeners();
    }

    private void setListeners() {
        OnClick onclick = new OnClick();
        mBtnTextView.setOnClickListener(onclick);
        mBtnEditText.setOnClickListener(onclick);
        mBtnButton.setOnClickListener(onclick);
        mRadioBtn.setOnClickListener(onclick);
        mcheckBox.setOnClickListener(onclick);
        mImageView.setOnClickListener(onclick);
        mListView.setOnClickListener(onclick);
        mBtnGridView.setOnClickListener(onclick);
        mBtnRecyclerView.setOnClickListener(onclick);
        mBtnWebView.setOnClickListener(onclick);
        mBtnToast.setOnClickListener(onclick);
        mBtnDialog.setOnClickListener(onclick);
        mBtnProgress.setOnClickListener(onclick);
        mBtnCustomDialog.setOnClickListener(onclick);
        mBtnPopUpWindow.setOnClickListener(onclick);
        mBtnFragment.setOnClickListener(onclick);
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()) {
                case R.id.btn_textview:
                    intent = new Intent(UIActivity.this, TextViewActivity.class);
                    break;

                case R.id.btn_button:
                    intent = new Intent(UIActivity.this, ButtonActivity.class);
                    break;

                case R.id.edittext:
                    intent = new Intent(UIActivity.this, EditTextActivity.class);
                    break;

                case R.id.btn_radioButton:
                    intent = new Intent(UIActivity.this, RadioButtonActivity.class);
                    break;

                case R.id.btn_checkBox:
                    intent = new Intent(UIActivity.this, CheckBoxActivity.class);
                    break;

                case R.id.btn_imageView:
                    intent = new Intent(UIActivity.this, ImageViewActivity.class);
                    break;

                case R.id.btn_listView:
                    intent = new Intent(UIActivity.this, ListViewActivity.class);
                    break;

                case R.id.btn_gridView:
                    intent = new Intent(UIActivity.this, GridViewActivity.class);
                    break;

                case R.id.btn_recyclerView:
                    intent = new Intent(UIActivity.this, RecyclerViewActivity.class);
                    break;

                case R.id.btn_webView:
                    intent = new Intent(UIActivity.this, WebViewActivity.class);
                    break;

                case R.id.btn_toast:
                    intent = new Intent(UIActivity.this, ToastActivity.class);
                    break;

                case R.id.btn_dialog:
                    intent = new Intent(UIActivity.this, DialogActivity.class);
                    break;

                case R.id.btn_progress:
                    intent = new Intent(UIActivity.this, ProgressActivity.class);
                    break;

                case R.id.btn_custom_dialog:
                    intent = new Intent(UIActivity.this, CustomDialogActivity.class);
                    break;

                case R.id.btn_popUpWindow:
                    intent = new Intent(UIActivity.this, PopupWindowActivity.class);
                    break;

                case R.id.btn_fragment:
                    intent = new Intent(UIActivity.this, ContainerActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}
