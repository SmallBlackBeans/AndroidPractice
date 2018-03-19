package com.example.helloworld.Basic.Music;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.helloworld.R;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MusicListActivity extends AppCompatActivity {

    @BindView(R.id.lv)
    ListView mLv;
    private MusicListAdapter mMusicAdapter;


    private ArrayList<String> mMusicFilePath;
    private ServiceConnection mConnection;
    private IMusicInterface mMusicService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);
        ButterKnife.bind(this);
        //初始化UI
        initUI();
        //从SD卡中初始化数据
        initData();

        mMusicAdapter.setDatas(mMusicFilePath);
        mMusicAdapter.notifyDataSetChanged();

        //启动并绑定服务
        Intent intent = new Intent(this, MusicService.class);
        startService(intent);
        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder binder) {
                mMusicService = (IMusicInterface) binder;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bindService(intent, mConnection, BIND_AUTO_CREATE);
        //设置播放模式


    }

    //创建菜单栏
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;//自己处理菜单的显示
    }


    //菜单点击回调
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences sp = getSharedPreferences("musicMode", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.stop_when_over:
                Toast.makeText(MusicListActivity.this, "stop_when_over", Toast.LENGTH_SHORT).show();
                editor.putInt("mode", 0);
                break;
            case R.id.single_loop:
                Toast.makeText(MusicListActivity.this, "single_loop", Toast.LENGTH_SHORT).show();
                editor.putInt("mode", 1);
                break;
            case R.id.all_loop:
                Toast.makeText(MusicListActivity.this, "all_loop", Toast.LENGTH_SHORT).show();
                editor.putInt("mode", 2);
                break;

            case R.id.logout:
                mMusicService.callStopMusic();
                Intent intent = new Intent(this, MusicService.class);
                unbindService(mConnection);
                finish();
                break;

        }
        editor.commit();
        return super.onOptionsItemSelected(item);

    }

    //1.初始化UI
    private void initUI() {
        mMusicAdapter = new MusicListAdapter();
        mLv.setAdapter(mMusicAdapter);
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO: 点击
                mMusicService.callPlayMusic(mMusicFilePath, position);
            }
        });
    }


    private void initData() {
        mMusicFilePath = new ArrayList<>();
        //获取SD卡路径 
        File sdDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        //循环判断子文件
        File[] files = sdDir.listFiles();
        for (File file : files) {
            //判断是否是音乐
            String absolutePath = file.getAbsolutePath();
            if (absolutePath.endsWith("mp3")) {
                mMusicFilePath.add(absolutePath);
            }
        }
    }


}
