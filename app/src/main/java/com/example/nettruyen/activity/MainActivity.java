package com.example.nettruyen.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.nettruyen.R;
import com.example.nettruyen.adapter.HomeAdapter;
import com.example.nettruyen.adapter.StoryAdapter;
import com.example.nettruyen.data.Constants;
import com.example.nettruyen.event.MessageEvent;
import com.example.nettruyen.model.BannerData;
import com.example.nettruyen.model.request.Section;
import com.example.nettruyen.model.response.BaseResponse;
import com.example.nettruyen.model.response.HomeResponse;
import com.example.nettruyen.model.response.Story;
import com.example.nettruyen.network.ApiManager;
import com.stx.xhb.androidx.XBanner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    String[] images = Constants.images;
    List<Section> listSection = new ArrayList<>();
    RecyclerView rvHome;
    HomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiHome();
        initBanner();

        //B1: Recyclerview
        rvHome = findViewById(R.id.rvHome);

        //B2: Layout Manager
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        //B3: Adapter
        adapter = new HomeAdapter(this, listSection);

        //B4: Data
        rvHome.setLayoutManager(layoutManager);
        rvHome.setHasFixedSize(true);
        rvHome.setAdapter(adapter);
    }

    private void apiHome() {
        ApiManager.getService().apiHome().enqueue(new Callback<BaseResponse<HomeResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<HomeResponse>> call, Response<BaseResponse<HomeResponse>> response) {
                Log.d("TAG", "onResponse: ");
                if (response.body() != null) {
                    HomeResponse res = response.body().getData();
                    initData(res);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<HomeResponse>> call, Throwable t) {
                Log.d("TAG", "onFailure: ");
            }
        });
    }
    private void initBanner() {
        XBanner mXBanner = (XBanner) findViewById(R.id.xbanner);
        mXBanner.setBannerPlaceholderImg(R.mipmap.ic_launcher, ImageView.ScaleType.CENTER_CROP);
        final List<BannerData> datas = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            BannerData model = new BannerData(images[i], "Banner 1");
            datas.add(model);
        }
        mXBanner.setBannerData(datas);

        mXBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                BannerData data = datas.get(position);
                Glide.with(MainActivity.this).load(data.getXBannerUrl()).into((ImageView) view);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void initData(HomeResponse res) {
        createSection("All", res.getTrending());
        createSection("Hot", res.getHot());
        createSection("normal", res.getNormal());
        adapter.reloadData(listSection);
    }
    private void createSection(String title, List<Story> listStories){
        StoryAdapter adapter = new StoryAdapter(this, listStories);
        Section section = new Section(title, listStories, adapter);
        listSection.add(section);
    }
    private void goToDetailMovie(Story story) {
        Intent intent = new Intent(MainActivity.this, MainDetailActivity.class);
        intent.putExtra("STORY_NAME", story.getName());
        intent.putExtra("STORY", story);
        startActivity(intent);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent.StoryEvent event) {
        Story story = event.story;
        goToDetailMovie(story);
    }
}