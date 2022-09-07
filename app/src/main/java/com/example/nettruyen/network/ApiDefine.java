package com.example.nettruyen.network;

import com.example.nettruyen.model.request.LoginRequest;
import com.example.nettruyen.model.response.BaseResponse;
import com.example.nettruyen.model.response.HomeResponse;
import com.example.nettruyen.model.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiDefine {
    String URL = "http://10.0.2.2:8080";
//    String URL = "https://springfilm.herokuapp.com";
    @POST("/api/v1/login")
    Call<BaseResponse<LoginResponse>> apiLogin(@Body LoginRequest request);

    @GET("/api/home")
    Call<BaseResponse<HomeResponse>> apiHome();

}
