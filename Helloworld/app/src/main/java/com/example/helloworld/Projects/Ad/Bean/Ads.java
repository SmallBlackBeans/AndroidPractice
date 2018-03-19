package com.example.helloworld.Projects.Ad.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hanchenghai on 2018/3/19.
 */

public class Ads  implements Serializable {
    int next_req;
    List<AdDetail> ads;



    public int getNext_req() {
        return next_req;
    }

    public void setNext_req(int next_req) {
        this.next_req = next_req;
    }

    public List<AdDetail> getAds() {
        return ads;
    }

    public void setAds(List<AdDetail> ads) {
        this.ads = ads;
    }


    @Override
    public String toString() {
        return "Ads{" +
                "next_req=" + next_req +
                ", ads=" + ads +
                '}';
    }

}


