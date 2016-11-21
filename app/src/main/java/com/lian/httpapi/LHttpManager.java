package com.lian.httpapi;


import android.content.Context;
import android.widget.ImageView;

import com.lian.model.LBaseModel;
import com.lian.picasso.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lianwanfei on 16/11/2.
 */
public class LHttpManager {
    public  static final String API_BASE_URL = "http://172.20.16.168:8080";
    public static final  int DEFAULT_TIMEOUT = 10;

    public void getMenuList(String store_id, final LHttpCallBack callBack){
        Map queryMap = new HashMap();
        queryMap.put("store_id", store_id);
        Call<LBaseModel> call = api.register(queryMap);
        addCallBack(call,callBack);
    }
    public void register(String username, final LHttpCallBack callBack){
        Map queryMap = new HashMap();
        queryMap.put("username", username);
        Call<LBaseModel> call = api.register(queryMap);
        addCallBack(call,callBack);
    }
    public void login(String username, String pass,final LHttpCallBack callBack){
        Map queryMap = new HashMap();
        queryMap.put("username", username);
        queryMap.put("pass", pass);
        Call<LBaseModel> call = api.login(queryMap);
        addCallBack(call,callBack);
    }

    public void showImage(Context cxt,String url, ImageView imageView){
        Picasso.with(cxt).load(url).placeholder(R.drawable.ic_launcher).into(imageView);
    }


    private static LHttpManager lHttpManager;
    private static RetrofitApi api;
    private static OkHttpClient client;
    private static Retrofit retrofit;
    public static LHttpManager getInstance(){
        if(lHttpManager==null) {
            lHttpManager = new LHttpManager();
            init();
        }
        return lHttpManager;
    }
    public static void init() {
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
    public void addCallBack(Call<LBaseModel> call , final LHttpCallBack callBack) {
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


}
