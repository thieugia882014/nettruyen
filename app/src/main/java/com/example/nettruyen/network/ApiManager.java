package com.example.nettruyen.network;

import com.example.nettruyen.data.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private static ApiManager instance;
    private static ApiDefine service;

    private ApiManager() {
    }

    public static ApiManager getInstance() {
        if (instance == null) {
            instance = new ApiManager();

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    // Request customization: add request headers
                    String token = "";
                    if (Constants.token != null) {
                        token = Constants.token;
                    }
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", token)
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });

            OkHttpClient client = httpClient.build();
            service = new Retrofit.Builder()
                    .baseUrl(ApiDefine.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build().create(ApiDefine.class);
        }
        return instance;
    }

    public static ApiDefine getService() {
        if (service == null) {
            getInstance();
        }
        return service;
    }
}