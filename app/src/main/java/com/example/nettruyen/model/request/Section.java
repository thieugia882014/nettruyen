package com.example.nettruyen.model.request;

import com.example.nettruyen.adapter.StoryAdapter;
import com.example.nettruyen.model.response.Story;

import java.util.List;

public class Section {
    private String title;
    private List<Story> listStory;
    private StoryAdapter adapter;

    public Section() {
    }

    public Section(String title, List<Story> listStory, StoryAdapter adapter) {
        this.title = title;
        this.listStory = listStory;
        this.adapter = adapter;
    }

    public StoryAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(StoryAdapter adapter) {
        this.adapter = adapter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Story> getListMovie() {
        return listStory;
    }

    public void setListMovie(List<Story> listStory) {
        this.listStory = listStory;
    }
}
