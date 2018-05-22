package com.example.helloworld.Basic.Service;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.android.internal.telephony.ITelephony;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hanchenghai on 2018/3/12.
 */

public class CallService extends Service {


    private MediaRecorder mRecorder;
    private String mIncomingNumber;
    private boolean mIsRecording;

    @Override
    public void onCreate() {
        super.onCreate();
        TelephonyManager manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        manager.listen(new PhoneStateListener() {
            //重写一些方法
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                super.onCallStateChanged(state, incomingNumber);
                switch (state) {
                    case TelephonyManager.CALL_STATE_IDLE:
                        Log.v("hanxiaocu", "挂断");
                        if (mRecorder != null && mIsRecording) {
                            mRecorder.stop();
                            mRecorder.release();
                            mRecorder = null;
                            mIsRecording = false;
                        }
                        break;
                    case TelephonyManager.CALL_STATE_RINGING:
                        Log.v("hanxiaocu", "响铃");
                        mIncomingNumber = incomingNumber;
                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                        Log.v("hanxiaocu", "接通");
                        startRecoder();
                        break;
                }
            }
        }, PhoneStateListener.LISTEN_CALL_STATE);

    }


    /**
     * 录音
     */
    private void startRecoder() {
        mRecorder = new MediaRecorder();

        //音频的来源
        //VOICE_CALL 实际打电话
        //MIC 开发使用
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        //生成的音频文件格式 .3pg .mp3 .mp4
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        //保存路径
        mRecorder.setOutputFile(getRecordFilePath(mIncomingNumber));
        //音频内部解码
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mRecorder.start();
        mIsRecording = true;
    }


    /**
     * 获取录音保存路径
     *
     * @param phone
     * @return
     */
    private String getRecordFilePath(String phone) {
        //电话号码 + “#” + 时间 + ".3gp"
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd hh:mm");
        String fileName = phone + "#" + format.format(new Date()) + ".3gp";
        File file = new File(getFilesDir(), fileName);
        return file.getAbsolutePath();
    }



    private void deleteCallLog(String phoneNum) {
        //获取内容解析器
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://call_log/calls");
        resolver.delete(uri,"number=?",new String[]{phoneNum});
    }





    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    class CallListener extends PhoneStateListener {

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE://空闲
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK://接通
                    endCall();
                    break;
                case TelephonyManager.CALL_STATE_RINGING://响铃
                    break;
            }
            super.onCallStateChanged(state, incomingNumber);
        }
    }

    private void endCall() {
        try {
            /*反射拦截电话*/
            Class<?> clazz = CallService.class.getClassLoader().loadClass("android.os.ServiceManager");
            Method method = clazz.getDeclaredMethod("getService", String.class);
            IBinder binder = (IBinder) method.invoke(null, TELEPHONY_SERVICE);
            ITelephony iTelephony = ITelephony.Stub.asInterface(binder);
            iTelephony.endCall();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
