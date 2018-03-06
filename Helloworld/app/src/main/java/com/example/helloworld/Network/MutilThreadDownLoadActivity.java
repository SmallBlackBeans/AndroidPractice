package com.example.helloworld.Network;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.helloworld.R;

import org.xutils.x;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.client.utils.HttpClientUtils;

public class MutilThreadDownLoadActivity extends AppCompatActivity {

    private static final int MAX_THREAD_NUM = 3;

    private int downloadNum;


    @BindView(R.id.pbcontainer)
    LinearLayout mPbcontainer;

    @BindView(R.id.btn_mutildown)
    Button mBtnMutildown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutil_thread_down_load);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_mutildown)
    public void onViewClicked() {
        mPbcontainer.removeAllViews();
        for (int i = 0; i < MAX_THREAD_NUM; i++) {
            LayoutInflater inflater = LayoutInflater.from(this);
            View view = inflater.inflate(R.layout.layout_download_progress, null);
            mPbcontainer.addView(view);
        }


        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL(getUrlPath());
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    if (conn.getResponseCode() == 200) {
                        downloadNum = 0;
                        int contentLength = conn.getContentLength();
                        File file = new File(getFilesDir(), getFileName());
                        RandomAccessFile raf = new RandomAccessFile(file, "rw");
                        raf.setLength(contentLength);

                        int perSize = contentLength / MAX_THREAD_NUM;
                        for (int i = 0; i < MAX_THREAD_NUM; i++) {
                            int startPosition = i * perSize; //从0 开始
                            int endPosition = (i + 1) * perSize - 1;
                            Log.v("hanxiaocu", "开始下载: startPosition " + startPosition + " endPosition" + endPosition);
                            if (i == MAX_THREAD_NUM) {
                                endPosition = contentLength - 1;
                            }
                            new DownloadThread(i, startPosition, endPosition).start();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }





    class DownloadThread extends Thread {
        private int mThreadIndex;
        private int mStartPosition;
        private int mEndPosition;
        private int mLastPosition;


        public DownloadThread(int threadIndex, int startPosition, int endPosition) {
            mThreadIndex = threadIndex;
            mStartPosition = startPosition;
            mEndPosition = endPosition;
            mLastPosition = startPosition;
        }

        @Override
        public void run() {
            super.run();

            View view = mPbcontainer.getChildAt(mThreadIndex);
            final ProgressBar progressBar = view.findViewById(R.id.progressBar);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressBar.setMax(mEndPosition - mStartPosition);
                }
            });

            try {
                File lastRecordFile = getRecordFile(mThreadIndex, getFileName());
                if (lastRecordFile.exists() && lastRecordFile.length() > 0) {
                    FileInputStream fis = new FileInputStream(lastRecordFile);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                    mLastPosition = Integer.parseInt(reader.readLine());
                }


                URL url = new URL(getUrlPath());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                ///这里忘记了 错写成了mStartPosition
                conn.setRequestProperty("Range", "bytes=" + mLastPosition + "-" + mEndPosition);
                /////
                if (conn.getResponseCode() == 206) {
                    InputStream is = conn.getInputStream();

                    File file = new File(getFilesDir(), getFileName());
                    RandomAccessFile raf = new RandomAccessFile(file, "rw");
                    ///这里忘记了
                    raf.skipBytes(mLastPosition);
                    ///
                    byte[] buff = new byte[1024];
                    int readLength;
                    while ((readLength = is.read(buff)) > 0) {
                        raf.write(buff, 0, readLength);
                        mLastPosition += readLength;
                        //实时保存到硬盘中
                        RandomAccessFile recordRaf = new RandomAccessFile(lastRecordFile, "rwd");
                        recordRaf.write((mLastPosition + "").getBytes());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setProgress(mLastPosition - mStartPosition);
                            }
                        });
                    }
                    raf.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                downloadNum++;
                if (downloadNum == MAX_THREAD_NUM) {
                    Log.v("hanxiaocu", "下载完成！");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MutilThreadDownLoadActivity.this,"下载完成",Toast.LENGTH_LONG).show();
                        }
                    });
                    for (int i = 0; i < MAX_THREAD_NUM; i++) {
                        File recordFile = getRecordFile(i, getFileName());
                        recordFile.delete();
                    }
                }

            }

        }
    }


    private File getRecordFile(int threadIndex, String fileName) {
        fileName = fileName.substring(0, fileName.lastIndexOf("."));
        fileName += ("#" + threadIndex + ".txt");
        return new File(getFilesDir(), fileName);
    }


    private String getUrlPath() {
        return "http://192.168.0.65:8090/AndroidDemo/resource.rar";
    }

    private String getFileName() {
        return getUrlPath().substring(getUrlPath().lastIndexOf("/") + 1);
    }


}
