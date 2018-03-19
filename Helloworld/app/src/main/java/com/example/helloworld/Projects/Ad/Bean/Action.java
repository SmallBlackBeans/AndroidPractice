package com.example.helloworld.Projects.Ad.Bean;

import java.io.Serializable;

public class Action implements Serializable {
    @Override
    public String toString() {
        return "Action{" +
                "link_url='" + link_url + '\'' +
                '}';
    }

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }

    public String link_url;
}
