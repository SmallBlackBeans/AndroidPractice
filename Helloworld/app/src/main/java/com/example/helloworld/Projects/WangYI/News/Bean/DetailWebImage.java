package com.example.helloworld.Projects.WangYI.News.Bean;

import java.io.Serializable;

/**
 * Created by hanchenghai on 2018/4/6.
 */

public class DetailWebImage implements Serializable {

    /**
     * ref : <!--IMG#0-->
     * src : http://cms-bucket.nosdn.127.net/catchpic/8/8b/8b309109e6e714a551167a49ec357ad5.gif
     * alt :
     */

    private String ref;
    private String src;
    private String alt;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }
}
