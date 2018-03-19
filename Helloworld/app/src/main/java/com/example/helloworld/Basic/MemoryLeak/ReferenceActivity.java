package com.example.helloworld.Basic.MemoryLeak;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.helloworld.R;

import java.lang.ref.WeakReference;

public class ReferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference);
    }



    private static class Mythread extends Thread {

        private WeakReference<ReferenceActivity> mActivityWeakReference = null;

        Mythread(ReferenceActivity activity) {
            mActivityWeakReference = new WeakReference<ReferenceActivity>(activity);
        }

        @Override
        public void run() {
            ReferenceActivity activity = mActivityWeakReference.get();
            if (activity != null) {
                super.run();
                for (int i = 0; i < 100; i++) {
                    if (!activity.isDestroyed()) {
                        Log.d("hanxiaocu", "run: " + i);
                        SystemClock.sleep(1000);
                    }
                }
            }

        }
    }

}
