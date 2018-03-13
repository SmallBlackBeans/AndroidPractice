package com.example.helloworld.RemoteService;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by hanchenghai on 2018/3/12.
 */

/*
1.设计继承类
2.注册服务类
3.提供内部的实际业务方法
4.为代理声明一个服务的规范（接口）/ aidl
5.声明一个代理类继承Binder 并实现了约束规范的接口 aidl.stub()  nb
6.onBind中指定代理对象



进程间通信
AIDL 接口定义语言



bindService()-> new ServiceConnection
onServiceConnected() 调用代理的方法
代理调用服务的业务方法

*/
public class AlipayService extends Service {

    private IBinder mAlipayAgent = new IAlipayInterface.Stub() {
        @Override
        public int callSafePay(String account, String pwd, String payPwd, double money, long currentTimeMiles) throws RemoteException {
            Log.v("hanxiaocu","远程调用");
            return 0;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mAlipayAgent;
    }


    //内部业务方法

    /**
     * @param account          账号
     * @param pwd              密码
     * @param payPwd           支付密码
     * @param money            支付金额
     * @param currentTimeMiles 当前时间戳
     * @return int 状态码
     */
    public int safePay(String account, String pwd, String payPwd, double money, long currentTimeMiles) {
        return 1;
    }
}
