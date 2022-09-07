package com.example.nettruyen.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.nettruyen.R;
import com.example.nettruyen.data.Constants;
import com.example.nettruyen.model.request.LoginRequest;
import com.example.nettruyen.model.response.BaseResponse;
import com.example.nettruyen.model.response.LoginResponse;
import com.example.nettruyen.model.response.UserResponse;
import com.example.nettruyen.network.ApiManager;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText edUsername, edPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        Button btLogin = findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiLogin();
            }
        });
        getUser();
    }

    private void apiLogin() {
        String username = edUsername.getText().toString();
        String password = edPassword.getText().toString();
        if (!validate(username, password)){
            return;
        }
        onLogin(username,password);
        }
    private void onLogin(String username, String password) {
//        if (username.equalsIgnoreCase("abc") && password.equalsIgnoreCase("123")) {
//            openMain();
//        } else {
//            Toast.makeText(LoginActivity.this, "sai tai koan", Toast.LENGTH_LONG).show();
//        }

        LoginRequest request = new LoginRequest(username, password);
        ApiManager.getService().apiLogin(request).enqueue(new Callback<BaseResponse<LoginResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<LoginResponse>> call, Response<BaseResponse<LoginResponse>> response) {
                Log.d("TAG", "onResponse: ");
                BaseResponse<LoginResponse> res = response.body();
                LoginResponse data = res.getData();
                saveUser(data);
            }

            @Override
            public void onFailure(Call<BaseResponse<LoginResponse>> call, Throwable t) {
                Log.d("TAG", "onFailure: ");
            }
        });
    }

    private void getUser() {
        SharedPreferences pref = getSharedPreferences("MOVIE", MODE_MULTI_PROCESS);
        Constants.token = pref.getString("token", null);
        String userJson = pref.getString("user", null);
        UserResponse user = new Gson().fromJson(userJson, UserResponse.class);
        Constants.user = user;
        if (user != null) {
            openMain();
        }
    }

    private void saveUser(LoginResponse data) {
        SharedPreferences pref = getSharedPreferences("Novel", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("token", data.getAccessToken());
        editor.putString("refreshToken", data.getRefreshToken());
        Gson gson = new Gson();
        editor.putString("user", gson.toJson(data.getAccount()));
        editor.apply();
        openMain();
    }

    private void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean validate(String username, String password) {
        if (!username.isEmpty() && !password.isEmpty()) {
            return true;
        }
        Toast.makeText(this, "Ban phai nhap username va password", Toast.LENGTH_SHORT).show();
        return false;
    }
}