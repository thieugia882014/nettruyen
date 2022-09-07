package com.example.nettruyen.model.response;

import java.util.List;

public class HomeResponse {
    private List<Story> trending;
    private List<Story> hot;
    private List<Story> normal;

    public List<Story> getTrending() {
        return trending;
    }

    public void setTrending(List<Story> trending) {
        this.trending = trending;
    }

    public List<Story> getHot() {
        return hot;
    }

    public void setHot(List<Story> hot) {
        this.hot = hot;
    }

    public List<Story> getNormal() {
        return normal;
    }

    public void setPopular(List<Story> normal) {
        this.normal = normal;
    }
}
