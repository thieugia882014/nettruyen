package com.example.nettruyen.event;

import com.example.nettruyen.model.response.Story;

public class MessageEvent {

    public static class StoryEvent {
        public Story story;

        public StoryEvent(Story story) {
            this.story = story;
        }
    }

}
