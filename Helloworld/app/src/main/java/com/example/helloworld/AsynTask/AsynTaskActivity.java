package com.example.helloworld.AsynTask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.example.helloworld.R;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.HttpConnection;

public class AsynTaskActivity extends AppCompatActivity {

    @BindView(R.id.btn_aysn)
    Button mBtnAysn;

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

        MyAsyncTask asyncTask = new MyAsyncTask();
        asyncTask.execute(Constant.SPLASH_URL);

    }


    private class MyAsyncTask extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... strings) {
            //请求网络
            String result = "";
            ByteArrayOutputStream outputStream = null;
            InputStream inputStream = null;
            URL url = null;
            try {
                url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Charset", "utf-8");
                connection.setConnectTimeout(5000);
                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    inputStream = connection.getInputStream();

                    outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int length = -1;
                    while ((length = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, length);
                    }
                    byte[] bytes = outputStream.toByteArray();
                    result = new String(bytes);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
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

            return null;
        }

        //后台执行后 去更新UI界面
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(getClass().getSimpleName(),"onPreExecute" + s);
        }

        //在后台执行任务之前 一般做一些初始化操作
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }


        //进度更新时候调用
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }


}
