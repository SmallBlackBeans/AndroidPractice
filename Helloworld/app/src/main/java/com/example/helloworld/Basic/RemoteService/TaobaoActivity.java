package com.example.helloworld.Basic.RemoteService;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.helloworld.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaobaoActivity extends AppCompatActivity {

    @BindView(R.id.btn_pay)
    Button mBtnPay;
    private ServiceConnection mServiceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taobao);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_pay)
    public void onViewClicked() {
        Intent intent = new Intent();
        //隐式调用
        intent.setAction("com.example.helloworld.action.ALiPAY");
        mServiceConnection = new ServiceConnection() {
            //在该方法中返回代理
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                IAlipayInterface alipayInterface = IAlipayInterface.Stub.asInterface(service);
                try {
                    int payResult = alipayInterface.callSafePay("hanxiaocu", "123", "123456", 1000, System.currentTimeMillis());
                    switch (payResult) {
                        case 0:
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }
}
