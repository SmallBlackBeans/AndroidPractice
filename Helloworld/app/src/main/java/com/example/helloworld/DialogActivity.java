package com.example.helloworld;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.helloworld.utils.ToastUtil;

public class DialogActivity extends AppCompatActivity {

    private Button mBtn1, mBtn2, mBtn3, mBtn4, mBtn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        OnclickLitener onclick = new OnclickLitener();
        bindViewListener(mBtn1, R.id.btn_dialog1, onclick);
        bindViewListener(mBtn2, R.id.btn_dialog2, onclick);
        bindViewListener(mBtn3, R.id.btn_dialog3, onclick);
        bindViewListener(mBtn4, R.id.btn_dialog4, onclick);
        bindViewListener(mBtn5, R.id.btn_custom, onclick);


    }

    private void bindViewListener(Button btn, int id, OnclickLitener onclick) {
        btn = findViewById(id);
        btn.setOnClickListener(onclick);
    }


    private class OnclickLitener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_dialog1:
                    final AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);
                    builder.setIcon(R.drawable.icon_username)
                            .setTitle("标题")
                            .setMessage("内容是这个")
                            .setPositiveButton("棒", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ToastUtil.showMsg(DialogActivity.this, "哈哈");
                                }
                            })
                            .setNeutralButton("还行", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .setNegativeButton("不要", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).show();

                    break;
                case R.id.btn_dialog2:
                    final String[] array = new String[]{"男", "女"};
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(DialogActivity.this);
                    builder1
                            .setTitle("选择性别")
                            .setItems(array, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ToastUtil.showMsg(DialogActivity.this, array[i]);
                                }
                            }).show();
                    break;
                case R.id.btn_dialog3:
                    final String[] array3 = new String[]{"男", "女"};
                    AlertDialog.Builder builder3 = new AlertDialog.Builder(DialogActivity.this);
                    builder3.setTitle("选择性别")
                            .setSingleChoiceItems(array3, 0, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).setCancelable(false).show();
                    break;
                case R.id.btn_dialog4:
                    final String[] array4 = new String[]{"坐飞机", "坐汽车", "坐轮船"};
                    boolean[] isSelected = new boolean[]{false, false, true};
                    AlertDialog.Builder builder4 = new AlertDialog.Builder(DialogActivity.this);
                    builder4.setTitle("选择出行方式")
                            .setMultiChoiceItems(array4, isSelected, new DialogInterface.OnMultiChoiceClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                    ToastUtil.showMsg(DialogActivity.this, array4[i] + ":" + b);
                                }
                            })
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .show();
                    break;
                case R.id.btn_custom:
                    AlertDialog.Builder builder5 = new AlertDialog.Builder(DialogActivity.this);
                    View loginView = LayoutInflater.from(DialogActivity.this).inflate(R.layout.layout_dialog_login, null);
                    Button loginBtn = loginView.findViewById(R.id.login_btn);
                    loginBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                    builder5.setTitle("请登录")
                            .setView(loginView).show();
                    break;
            }
        }
    }
}
