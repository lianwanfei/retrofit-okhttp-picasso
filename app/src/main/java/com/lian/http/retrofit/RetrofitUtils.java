package com.lian.http.retrofit;

import com.lian.http.LHttpCallBack;
import com.lian.http.LHttpManager;
import com.lian.model.LBaseModel;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lianwanfei on 16/11/4.
 */

public class RetrofitUtils {

    private RetrofitApi api;
    private  OkHttpClient client;
    private  Retrofit retrofit;

    public void getByRetrofit(Map queryMap , final LHttpCallBack callBack) {
        Call<LBaseModel> call = api.get(queryMap);
        call.enqueue(new retrofit2.Callback<LBaseModel>() {
            @Override
            public void onResponse(Call<LBaseModel> call, retrofit2.Response<LBaseModel> response) {
                try {
                    callBack.onResponse(response.body());
                } catch (IOException e) {
                    callBack.onFailure("");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<LBaseModel> call, Throwable t) {
                callBack.onFailure(t.toString());
            }
        });

    }


    public void init() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()
                .connectTimeout(LHttpManager.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(LHttpManager.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(LHttpManager.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(LHttpManager.API_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(RetrofitApi.class);
    }
}
