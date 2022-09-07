package com.example.nettruyen.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nettruyen.R;
import com.example.nettruyen.model.request.Section;
import com.example.nettruyen.model.response.Story;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter {

    Activity activity;
    List<Section> listSection;

    public HomeAdapter(Activity activity, List<Section> listSection) {
        this.activity = activity;
        this.listSection = listSection;
    }

    public void reloadData(List<Section> listSection) {
        this.listSection = listSection;
        this.notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = activity.getLayoutInflater().inflate(R.layout.activity_main, parent, false);
        SectionHolder holder = new SectionHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SectionHolder vh = (SectionHolder) holder;
        Section model = listSection.get(position);
        vh.tvTitle.setText(model.getTitle());
        //B1: Data
        List<Story> listStory = model.getListMovie();
        //B2: Layout Manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        //B3: Adapter
        StoryAdapter adapter = model.getAdapter();
        adapter.setSection(model.getTitle());
        //B4: RecyclerView
        vh.rvSection.setLayoutManager(layoutManager);
        vh.rvSection.setHasFixedSize(true);
        vh.rvSection.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return listSection.size();
    }

    public class SectionHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        RecyclerView rvSection;

        public SectionHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvMovie);
            rvSection = itemView.findViewById(R.id.tvMovie);
        }
    }
}
