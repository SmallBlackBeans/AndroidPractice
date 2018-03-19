package com.example.helloworld.Basic.Music;

import java.util.ArrayList;

/**
 * Created by hanchenghai on 2018/3/13.
 */

public interface IMusicInterface {

    /**
     * 播放音乐
     */
    public void callPlayMusic(ArrayList<String> paths, int position);


    /**
     * 停止音乐
     */
    public void callStopMusic();


    /**
     * 暂停音乐
     */
    public void callPauseMusic();
}
