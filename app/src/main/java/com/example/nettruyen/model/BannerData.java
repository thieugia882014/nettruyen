package com.example.nettruyen.model;

import com.stx.xhb.androidx.entity.BaseBannerInfo;

public class BannerData implements BaseBannerInfo {
    private String url;
    private String title;

    public BannerData(String url, String title) {
        this.url = url;
        this.title = title;
    }

    @Override
    public Object getXBannerUrl() {
        return url;
    }

    @Override
    public String getXBannerTitle() {
        return title;
    }
}
