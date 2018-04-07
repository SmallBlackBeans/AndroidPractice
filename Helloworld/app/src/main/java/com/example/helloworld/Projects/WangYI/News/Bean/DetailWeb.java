package com.example.helloworld.Projects.WangYI.News.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanchenghai on 2018/4/6.
 */

public class DetailWeb {

    String body;

    ArrayList<DetailWebImage> img;

    String source;

    String title;

    String ptime;

    @Override
    public String toString() {
        return "DetailWeb{" +
                "body='" + body + '\'' +
                ", img=" + img +
                ", source='" + source + '\'' +
                ", title='" + title + '\'' +
                ", ptime='" + ptime + '\'' +
                '}';
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ArrayList<DetailWebImage> getImg() {
        return img;
    }

    public void setImg(ArrayList<DetailWebImage> img) {
        this.img = img;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }
}
