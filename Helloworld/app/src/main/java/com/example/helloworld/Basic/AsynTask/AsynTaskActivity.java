package com.example.helloworld.Basic.AsynTask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.helloworld.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AsynTaskActivity extends AppCompatActivity {

    @BindView(R.id.btn_aysn)
    Button mBtnAysn;
    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asyn_task);
        ButterKnife.bind(this);

        //开启后台线程加载网络数据
        //new Thread();
        //ThreadPoolExecutor
        //Executors.newCachedThreadPool();...
    }


    @OnClick(R.id.btn_aysn)
    public void onViewClicked() {
        MyAsyncTask asyncTask = new MyAsyncTask(this);
        asyncTask.execute(Constant.SPLASH_URL);

    }


    private static class MyAsyncTask extends AsyncTask<String, Integer, String> {

        private WeakReference<AsynTaskActivity> mWeakReference = null;
        MyAsyncTask(AsynTaskActivity activity) {
            mWeakReference = new WeakReference<AsynTaskActivity>(activity);
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.d(getClass().getSimpleName(), "doInBackground");
            //请求网络
            String result = "";
            ByteArrayOutputStream outputStream = null;
            InputStream inputStream = null;
            URL url = null;
            try {
                url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept-Charset", "utf-8");
                connection.setRequestProperty("Content-type", "application/x-java-serialized-object");
                connection.setRequestProperty("Accept-Encoding", "identity");
                connection.connect();

                connection.setConnectTimeout(5000);
                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    int contentLength = connection.getContentLength();
                    inputStream = connection.getInputStream();

                    outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int length = -1;
                    int writeLength = 0;
                    while ((length = inputStream.read(buffer)) != -1) {
                        SystemClock.sleep(500);
                        outputStream.write(buffer, 0, length);
                        writeLength += length;
                        int progress = writeLength / contentLength * 100;

                        //通知更新
                        publishProgress(progress);
                    }
                    byte[] bytes = outputStream.toByteArray();
                    result = new String(bytes, "utf-8");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            return result;
        }

        //后台执行后 去更新UI界面
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(getClass().getSimpleName(), "onPostExecute" + s);
            AsynTaskActivity activity = mWeakReference.get();
            activity.mTextView.setText(s);
        }

        //在后台执行任务之前 一般做一些初始化操作
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(getClass().getSimpleName(), "onPreExecute");
            AsynTaskActivity activity = mWeakReference.get();
            activity.mProgressBar.setMax(100);
        }


        //进度更新时候调用
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            AsynTaskActivity activity = mWeakReference.get();
            activity.mProgressBar.setProgress(values[0]);
        }
    }


}
