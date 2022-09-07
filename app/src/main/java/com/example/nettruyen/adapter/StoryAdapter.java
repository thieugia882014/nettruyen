package com.example.nettruyen.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nettruyen.R;
import com.example.nettruyen.event.MessageEvent;
import com.example.nettruyen.model.response.Story;
//import com.google.android.gms.wearable.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<Story> listStories;
    private String section;

    public StoryAdapter(Activity activity, List<Story> listStories) {
        this.activity = activity;
        this.listStories = listStories;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = activity.getLayoutInflater().inflate(R.layout.nettruyen_item, parent, false);
        if (section.equalsIgnoreCase("Hot") || section.equalsIgnoreCase("popular")){
            itemView = activity.getLayoutInflater().inflate(R.layout.nettruyen_item_big_size, parent, false);
        }
        MovieHolder holder = new MovieHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MovieHolder mvHolder = (MovieHolder) holder;
        Story model = listStories.get(position);
        mvHolder.tvMovie.setText(model.getName());
        Glide.with(activity).load(model.getThumbnail()).into(mvHolder.ivCover);
    }

    @Override
    public int getItemCount() {
        return listStories.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        ImageView ivCover;
        TextView tvMovie;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            ivCover = itemView.findViewById(R.id.ivCover);
            tvMovie = itemView.findViewById(R.id.tvMovie);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Story story = listStories.get(getAdapterPosition());
                    EventBus.getDefault().post(new MessageEvent.StoryEvent(story));
                }
            });
        }
    }


}
